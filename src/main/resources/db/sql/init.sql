create or replace table users
(
    id bigint auto_increment primary key comment 'primary key',
    account varchar(255) not null comment '사용자 계정 ID',
    password varchar(255) not null comment '사용자 계정 비밀번호',
    role varchar(255) not null comment '사용자 역할',
    name varchar(255) not null comment '사용자 이름',
    email varchar(255) null comment '사용자 메일계정',
    phone varchar(255) null comment '사용자 전화번호',
    meta longtext collate utf8mb4_bin null comment '메타 json',
    creator_account varchar(255) null comment '등록자 계정',
    created_at datetime default current_timestamp() null comment '등록자 일시',
    updater_account varchar(255) null comment '수정자 계정',
    updated_at datetime null comment '수정 일시',
    deleter_account varchar(255) null comment '삭제자 계정',
    deleted_at datetime null comment '삭제 일시',
    workflow_state varchar(255) null comment '작업상태: 활성(active), 삭제(deleted) ...',
    constraint meta
        check (json_valid(`meta`))
) comment '사용자';

create or replace index users_account_index on users (account, workflow_state);

/* local-web : 2AD3537785B3995A5B86DB915B9E1399F447E188CBC29CBB20A5AA754B51FF38*/
insert into oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities
, access_token_validity, refresh_token_validity, additional_information)
values ('c2f87df4460b48bfa19b9d6a7794d217'
, '{bcrypt}$2a$10$s.1G.zqRLBjPqh3A2Qq8je7kXoFlLnsIZiwlEBbCr7C.Pht2w/45m'
, 'read,write,profile'
, 'password,authorization_code,refresh_token'
, 'ROLE_ADMIN'
, 86400
, 259300
, 'local-web');

/* gate-controller : 6D41BBD53B834A13E2D84F5FE95C033EE74C059C8D567D633E1636C1E3DC4DAF*/
insert into oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities
                                 , access_token_validity, refresh_token_validity, additional_information)
values ('420520cfc1b244b8a9d9f58195a7baeb'
       , '{bcrypt}$2a$10$SY0FmiWi9rD4gQdwLZ8pE.U9ItDqPVEi4fXjHxktR5/2ObXGI2S8u'
       , 'read,write,profile'
       , 'password,authorization_code,refresh_token'
       , 'ROLE_GATE'
       , -1
       , -1
       , 'gate-controller');

insert into users ( account, password, role, name, email, meta, workflow_state)
values ('super'
       , '{bcrypt}$2a$10$oVZdXhvbfM8lvBysHP9pSeYDIT8EzXz2zXlh6ARZ1INrsdSrsCmQG'
       , 'SUPER'
       , '슈퍼관리자'
       , 'parklab@blueintel.ai'
       , '{}'
       , 'unpublished');