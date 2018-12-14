-- Les comptes
-- --------------------------
INSERT INTO `m_db`.`user_app`
(
`email`,
`first_name`,
`last_name`,
`password`,
`sso_id`,
`state`)
VALUES
("linda49martin@gmail.com",
"Linda",
"Martin",
"no",
"lmartin",
"Provisional");


INSERT INTO `m_db`.`user_app`
(
`email`,
`first_name`,
`last_name`,
`password`,
`sso_id`,
`state`)
VALUES
(
"admin@gmail.com",
"admin",
"admin",
"azer",
"admin",
"Provisional");

INSERT INTO `m_db`.`user_app`
(
`email`,
`first_name`,
`last_name`,
`password`,
`sso_id`,
`state`)
VALUES
("linda49martin@gmail.com",
"Client1",
"Martin",
"no",
"client",
"Provisional");

INSERT INTO `m_db`.`user_profile`
(
`type`)
VALUES
(
"ADMIN");

INSERT INTO `m_db`.`user_profile`
(
`type`)
VALUES
(
"AGENT");

INSERT INTO `m_db`.`user_profile`
(
`type`)
VALUES
(
"CLIENT");

INSERT INTO `m_db`.`app_user_user_profile`
(`user_id`,
`user_profile_id`)
VALUES
(1,
2);

INSERT INTO `m_db`.`app_user_user_profile`
(`user_id`,
`user_profile_id`)
VALUES
(2,
1);

INSERT INTO `m_db`.`app_user_user_profile`
(`user_id`,
`user_profile_id`)
VALUES
(3,
3);

INSERT INTO `m_db`.`user_question`
(
`question`)
VALUES
("Quel est le nom de votre ami(e) d'enfance?");

INSERT INTO `m_db`.`user_question`
(
`question`)
VALUES
("Quel est le nom de famille de votre mère?");

INSERT INTO `m_db`.`user_question`
(
`question`)
VALUES
("Quel était votre matière préférée au collègue?");

INSERT INTO `m_db`.`user_question`
(
`question`)
VALUES
("Quel était le nom de votre animal de compagnie?");