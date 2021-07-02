create table IF NOT EXISTS oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

insert into oauth_client_details(
     client_id
    ,resource_ids
    ,client_secret
    ,scope
    ,authorized_grant_types
    ,web_server_redirect_uri
    ,authorities
    ,access_token_validity
    ,refresh_token_validity
    ,additional_information
    ,autoapprove)
values(
    'testId'
    ,null
    ,'{bcrypt}$2a$10$yn4o4jcEmw8c0wVjpImMa.Z5k/W5R/2oGy1XcEdb4dzCGeZWr3pDS'
    ,'read,write,profile'
    ,'password,authorization_code,refresh_token'
    ,null
    ,'ROLE_ADMIN'
    ,1060
    ,61060
    ,null
    ,null);

insert into oauth_client_details(
     client_id
    ,resource_ids
    ,client_secret
    ,scope
    ,authorized_grant_types
    ,web_server_redirect_uri
    ,authorities
    ,access_token_validity
    ,refresh_token_validity
    ,additional_information
    ,autoapprove)
values(
    'e46d628e42fa4d46a9c84423c38d2a2a'
    ,null
    ,'{bcrypt}$2a$10$Ak3TmefdLrvT77xyd6oRHuMzqU73H2LpZKwLcM7VOoT/3Phjs1AOe'
    ,'read,write,profile'
    ,'password,authorization_code,refresh_token'
    ,null
    ,'ROLE_ADMIN'
    ,-1
    ,-1
    ,null
    ,null);