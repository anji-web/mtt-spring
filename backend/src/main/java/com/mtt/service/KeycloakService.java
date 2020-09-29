package com.mtt.service;

import com.mtt.model.Users;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String url;

    @Value("${keycloak.realm}")
    private String REALM;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    private static final Logger logger = LoggerFactory.getLogger(KeycloakService.class);


    private UsersResource getUserResources(){
        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(url).realm("master").username("secretcode").password("2827180315")
                .clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
        RealmResource realmResource = keycloak.realm(REALM);
        UsersResource usersResource = realmResource.users();
        return usersResource;
    }

    private RealmResource getRealmResources(){
        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(url).realm("master").username("secretcode").password("2827180315")
                .clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
        RealmResource realmResource = keycloak.realm(REALM);
        return realmResource;
    }

    private String sendPost(List<NameValuePair> urlPost) throws Exception{
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url + "/realms/" + REALM + "/protocol/openid-connect/token" );
        post.setEntity(new UrlEncodedFormEntity(urlPost));

        HttpResponse response = client.execute(post);

        BufferedReader result = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        while ((line = result.readLine()) != null){
            buffer.append(line);
        }

        return buffer.toString();
    }

    public int createToken(Users users){
        int status = 0;
        try{

            UsersResource usersResource = getUserResources();
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(users.getEmail());
            userRepresentation.setEmail(users.getEmail());
            userRepresentation.setFirstName(users.getUserProfile().getFirstName());
            userRepresentation.setLastName(users.getUserProfile().getLastName());
            userRepresentation.setEnabled(true);

//            create user
            Response res = usersResource.create(userRepresentation);
            logger.info("Keycloak created user : " + res.getStatus());

            status = res.getStatus();

            if (status == 201){
                String UID = res.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                logger.info("User created UID : " + UID);

//                define password
                CredentialRepresentation credential = new CredentialRepresentation();
                credential.setType(CredentialRepresentation.PASSWORD);
                credential.setTemporary(false);
                credential.setValue(users.getPassword());

//                set password
                usersResource.get(UID).resetPassword(credential);

//                set role
                RealmResource realmResource = getRealmResources();
                RoleRepresentation roleRepresentation = realmResource.roles().get("Super Admin").toRepresentation();
                realmResource.users().get(UID).roles().realmLevel().add(Arrays.asList(roleRepresentation));
                logger.info("Email : " + users.getEmail() + " created in keycloak successfully");
            }else if (status == 409){
                logger.info("Email : " + users.getEmail() + " was already created in keycloak");

            }else {
                logger.error("Email : " + users.getEmail() + " not created created in keycloak");
            }




        }catch (RuntimeException exception){
            throw new RuntimeException("Bad Request : " + exception.getMessage());
        }
        return status;
    }




}
