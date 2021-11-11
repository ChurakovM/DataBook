DROP TABLE IF EXISTS `endusers`;
CREATE TABLE `endusers` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `user_name` varchar(50) DEFAULT NULL,
                          `email` varchar(50) DEFAULT NULL,
                          `encrypted_password` varchar(200) DEFAULT NULL,
                          PRIMARY KEY (`id`)
);