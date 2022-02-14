CREATE TABLE `accounts` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `id_user` int NOT NULL,
                            `first_name` varchar(45) NOT NULL,
                            `second_name` varchar(45) NOT NULL,
                            `phone` varchar(45) DEFAULT NULL,
                            `id_subscription` varchar(6) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `id_UNIQUE` (`id`),
                            UNIQUE KEY `id_user_UNIQUE` (`id_user`),
                            UNIQUE KEY `id_subscription_UNIQUE` (`id_subscription`),
                            CONSTRAINT `id_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `books` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `id_genre` int NOT NULL,
                         `author` varchar(45) NOT NULL,
                         `name` varchar(45) NOT NULL,
                         `publishing_house` varchar(45) NOT NULL,
                         `year_publishing` int NOT NULL,
                         `number_of_page` int NOT NULL,
                         `quantity` int NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id_UNIQUE` (`id`),
                         KEY `id_genres_idx` (`id_genre`),
                         CONSTRAINT `id_genres` FOREIGN KEY (`id_genre`) REFERENCES `genres` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `genres` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(45) NOT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `id_order_status` int NOT NULL DEFAULT '1',
                          `id_account` int NOT NULL,
                          `id_book` int NOT NULL,
                          `date_of_issue` datetime NOT NULL,
                          `return_date` datetime DEFAULT NULL,
                          `subscription` tinyint NOT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `idorders_UNIQUE` (`id`),
                          KEY `id_accounts_idx` (`id_account`),
                          KEY `id_books_idx` (`id_book`),
                          KEY `id_orders_status_idx` (`id_order_status`),
                          CONSTRAINT `id_accounts` FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                          CONSTRAINT `id_books` FOREIGN KEY (`id_book`) REFERENCES `books` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                          CONSTRAINT `id_orders_status` FOREIGN KEY (`id_order_status`) REFERENCES `orders_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders_status` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `name` varchar(45) NOT NULL,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `roles` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `login` varchar(20) NOT NULL,
                         `password` varchar(20) NOT NULL,
                         `id_role` int NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id_UNIQUE` (`id`),
                         UNIQUE KEY `login_UNIQUE` (`login`),
                         KEY `id_roles_idx` (`id_role`),
                         CONSTRAINT `id_roles` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
