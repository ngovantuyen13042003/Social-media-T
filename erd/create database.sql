DROP DATABASE IF EXISTS T;
CREATE DATABASE T;
USE T;
 
 CREATE TABLE roles(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) CHARACTER SET UTF8MB4,
    descriptions TEXT
 );
 
CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  fullname VARCHAR(255) CHARACTER SET UTF8MB4 NOT NULL,
  gender VARCHAR(10) CHARACTER SET UTF8MB4 NOT NULL,
  birthdate DATE NOT NULL,
  avatar VARCHAR(255) NULL,
  major VARCHAR(255) CHARACTER SET UTF8MB4 NULL,
  nickname VARCHAR(255) CHARACTER SET UTF8MB4 NULL,
  phone_number VARCHAR(20) NULL,
  company VARCHAR(255)CHARACTER SET UTF8MB4 NULL,
  address VARCHAR(255) CHARACTER SET UTF8MB4 NULL,
  school VARCHAR(255) CHARACTER SET UTF8MB4 NULL,
  friend_id BIGINT NULL,
  enabled bit(1),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id) ,
  FOREIGN KEY (friend_id) REFERENCES users (id)
);

 CREATE TABLE refresh_token (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id BIGINT NOT NULL,
    token VARCHAR(255) UNIQUE,
    expiry_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
 );

CREATE TABLE user_role (
	user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE posts (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  content TEXT NOT NULL,
  post_id BIGINT NULL,
  privacy VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL DEFAULT 'public',
  
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);





CREATE TABLE comments (
  id BIGINT NOT NULL AUTO_INCREMENT,
  content TEXT,
  post_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  parent_comment BIGINT NULL,
  
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (post_id) REFERENCES posts (id),
  FOREIGN KEY (parent_comment) REFERENCES comments (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);



CREATE TABLE conversations (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    conversation_name VARCHAR(100) CHARACTER SET UTF8MB4,
    last_message_id BIGINT 
);

CREATE TABLE user_conversation (
	user_id BIGINT NOT NULL ,
    conversation_id BIGINT NOT NULL,
    PRIMARY KEY(user_id, conversation_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (conversation_id) REFERENCES conversations (id)
);

CREATE TABLE messages (
  id BIGINT NOT NULL AUTO_INCREMENT,
  sender_id BIGINT NOT NULL,
  receiver_id BIGINT NOT NULL,
  content TEXT NOT NULL,
  conversation_id BIGINT NOT NULL,
  message_parent_id BIGINT NULL,
  is_read BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (sender_id) REFERENCES users (id),
  FOREIGN KEY (receiver_id) REFERENCES users (id),
  FOREIGN KEY (message_parent_id) REFERENCES messages (id),
  FOREIGN KEY (conversation_id) REFERENCES conversations (id)
);



CREATE TABLE pages (
  id BIGINT NOT NULL AUTO_INCREMENT,
  page_name VARCHAR(100) CHARACTER SET UTF8MB4,
  content TEXT NOT NULL,
  privacy VARCHAR(100) CHARACTER SET UTF8MB4 NOT NULL DEFAULT 'public',
  
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);



CREATE TABLE likes (
  id BIGINT NOT NULL AUTO_INCREMENT,
  
  page_id BIGINT  NULL,
  message_id BIGINT  NULL,
  post_id BIGINT  NULL,
  comment_id BIGINT NULL,
  user_id BIGINT NOT NULL,
  
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (page_id) REFERENCES pages (id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (post_id) REFERENCES posts (id),
  FOREIGN KEY (message_id) REFERENCES messages (id),
  FOREIGN KEY (comment_id) REFERENCES comments (id)
);



CREATE TABLE files (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NULL,
    message_id BIGINT NULL,
	page_id BIGINT NULL,
    url_file VARCHAR(255),
    FOREIGN KEY (post_id) REFERENCES posts (id),
	FOREIGN KEY (message_id) REFERENCES messages (id),
	FOREIGN KEY (page_id) REFERENCES pages (id)
    
);


-- Chèn dữ liệu vào bảng roles
INSERT INTO roles (name, descriptions)
VALUES
('Admin', 'Role for administrators'),
('User', 'Default role for regular users'),
('Moderator', 'Role for moderators'),
('Editor', 'Role for editors'),
('Subscriber', 'Role for subscribers'),
('Author', 'Role for authors'),
('Guest', 'Role for guests'),
('Manager', 'Role for managers'),
('Developer', 'Role for developers'),
('Tester', 'Role for testers');

-- Chèn dữ liệu vào bảng users
INSERT INTO users (username, email, password, fullname, gender, birthdate, avatar, major, nickname, phone_number, company, address, school, friend_id)
VALUES
('user1', 'user1@example.com', 'password1', 'User One', 'Male', '1990-01-01', NULL, 'Computer Science', 'U1', '1234567890', 'Company A', 'Address A', 'School A', NULL),
('user2', 'user2@example.com', 'password2', 'User Two', 'Female', '1995-02-02', NULL, 'Engineering', 'U2', '2345678901', 'Company B', 'Address B', 'School B', NULL),
('user3', 'user3@example.com', 'password3', 'User Three', 'Male', '1992-03-03', NULL, 'Mathematics', 'U3', '3456789012', 'Company C', 'Address C', 'School C', NULL),
('user4', 'user4@example.com', 'password4', 'User Four', 'Female', '1988-04-04', NULL, 'Physics', 'U4', '4567890123', 'Company D', 'Address D', 'School D', NULL),
('user5', 'user5@example.com', 'password5', 'User Five', 'Male', '1997-05-05', NULL, 'Chemistry', 'U5', '5678901234', 'Company E', 'Address E', 'School E', NULL),
('user6', 'user6@example.com', 'password6', 'User Six', 'Female', '1985-06-06', NULL, 'Biology', 'U6', '6789012345', 'Company F', 'Address F', 'School F', NULL),
('user7', 'user7@example.com', 'password7', 'User Seven', 'Male', '1993-07-07', NULL, 'Business Administration', 'U7', '7890123456', 'Company G', 'Address G', 'School G', NULL),
('user8', 'user8@example.com', 'password8', 'User Eight', 'Female', '1989-08-08', NULL, 'Economics', 'U8', '8901234567', 'Company H', 'Address H', 'School H', NULL),
('user9', 'user9@example.com', 'password9', 'User Nine', 'Male', '1994-09-09', NULL, 'Sociology', 'U9', '9012345678', 'Company I', 'Address I', 'School I', NULL),
('user10', 'user10@example.com', 'password10', 'User Ten', 'Female', '1991-10-10', NULL, 'Psychology', 'U10', '0123456789', 'Company J', 'Address J', 'School J', NULL);

-- Chèn dữ liệu vào bảng refresh_token (ví dụ)
INSERT INTO refresh_token (user_id, token, expiry_date)
VALUES
(1, 'token1', '2024-03-27 12:00:00'),
(2, 'token2', '2024-03-27 12:00:00'),
(3, 'token3', '2024-03-27 12:00:00'),
(4, 'token4', '2024-03-27 12:00:00'),
(5, 'token5', '2024-03-27 12:00:00'),
(6, 'token6', '2024-03-27 12:00:00'),
(7, 'token7', '2024-03-27 12:00:00'),
(8, 'token8', '2024-03-27 12:00:00'),
(9, 'token9', '2024-03-27 12:00:00'),
(10, 'token10', '2024-03-27 12:00:00');

-- Chèn dữ liệu vào bảng posts
INSERT INTO posts (user_id, content, privacy)
VALUES
(1, 'Post content 1', 'public'),
(2, 'Post content 2', 'public'),
(3, 'Post content 3', 'public'),
(4, 'Post content 4', 'public'),
(5, 'Post content 5', 'public'),
(6, 'Post content 6', 'public'),
(7, 'Post content 7', 'public'),
(8, 'Post content 8', 'public'),
(9, 'Post content 9', 'public'),
(10, 'Post content 10', 'public');

-- Chèn dữ liệu vào bảng comments
INSERT INTO comments (content, post_id, user_id, parent_comment)
VALUES
('Comment 1 on post 1', 1, 2, NULL),
('Comment 2 on post 1', 1, 3, NULL),
('Comment 3 on post 1', 1, 4, NULL),
('Reply to comment 1 on post 1', 1, 1, 1),
('Reply to comment 1 on post 1', 1, 5, 1),
('Comment 1 on post 2', 2, 1, NULL),
('Comment 2 on post 2', 2, 3, NULL),
('Comment 3 on post 2', 2, 4, NULL),
('Comment 1 on post 3', 3, 2, NULL),
('Comment 2 on post 3', 3, 3, NULL);

-- Chèn dữ liệu vào bảng conversations
INSERT INTO conversations (conversation_name)
VALUES
('Conversation 1'),
('Conversation 2'),
('Conversation 3'),
('Conversation 4'),
('Conversation 5'),
('Conversation 6'),
('Conversation 7'),
('Conversation 8'),
('Conversation 9'),
('Conversation 10');

-- Chèn dữ liệu vào bảng messages
INSERT INTO messages (sender_id, receiver_id, content, conversation_id, message_parent_id, is_read)
VALUES
(1, 2, 'Message 1 in conversation 1', 1, NULL, FALSE),
(2, 1, 'Message 2 in conversation 1', 1, NULL, FALSE),
(1, 3, 'Message 1 in conversation 2', 2, NULL, FALSE),
(3, 1, 'Message 2 in conversation 2', 2, NULL, FALSE),
(1, 4, 'Message 1 in conversation 3', 3, NULL, FALSE),
(4, 1, 'Message 2 in conversation 3', 3, NULL, FALSE),
(1, 5, 'Message 1 in conversation 4', 4, NULL, FALSE),
(5, 1, 'Message 2 in conversation 4', 4, NULL, FALSE),
(1, 6, 'Message 1 in conversation 5', 5, NULL, FALSE),
(6, 1, 'Message 2 in conversation 5', 5, NULL, FALSE);

-- Chèn dữ liệu vào bảng pages
INSERT INTO pages (page_name, content, privacy)
VALUES
('Page 1', 'Content of page 1', 'public'),
('Page 2', 'Content of page 2', 'public'),
('Page 3', 'Content of page 3', 'public'),
('Page 4', 'Content of page 4', 'public'),
('Page 5', 'Content of page 5', 'public'),
('Page 6', 'Content of page 6', 'public'),
('Page 7', 'Content of page 7', 'public'),
('Page 8', 'Content of page 8', 'public'),
('Page 9', 'Content of page 9', 'public'),
('Page 10', 'Content of page 10', 'public');



-- Chèn dữ liệu vào bảng files
INSERT INTO files (post_id, url_file)
VALUES
(1, 'url_of_file_for_post_1'),
(2, 'url_of_file_for_post_2'),
(3, 'url_of_file_for_post_3'),
(4, 'url_of_file_for_post_4'),
(5, 'url_of_file_for_post_5'),
(6, 'url_of_file_for_post_6'),
(7, 'url_of_file_for_post_7'),
(8, 'url_of_file_for_post_8'),
(9, 'url_of_file_for_post_9'),
(10, 'url_of_file_for_post_10');

-- Chèn dữ liệu vào bảng user_role
INSERT INTO user_role (user_id, role_id)
VALUES
(1, 1), -- Assigning Admin role to User 1
(2, 2), -- Assigning User role to User 2
(3, 2), -- Assigning User role to User 3
(4, 2), -- Assigning User role to User 4
(5, 2), -- Assigning User role to User 5
(6, 2), -- Assigning User role to User 6
(7, 2), -- Assigning User role to User 7
(8, 2), -- Assigning User role to User 8
(9, 2), -- Assigning User role to User 9
(10, 2); -- Assigning User role to User 10

-- Chèn dữ liệu vào bảng user_conversation
INSERT INTO user_conversation (user_id, conversation_id)
VALUES
(1, 1), -- User 1 is part of Conversation 1
(2, 1), -- User 2 is part of Conversation 1
(3, 1), -- User 3 is part of Conversation 1
(4, 2), -- User 4 is part of Conversation 2
(5, 2), -- User 5 is part of Conversation 2
(6, 3), -- User 6 is part of Conversation 3
(7, 3), -- User 7 is part of Conversation 3
(8, 4), -- User 8 is part of Conversation 4
(9, 4), -- User 9 is part of Conversation 4
(10, 4); -- User 10 is part of Conversation 4

-- Chèn dữ liệu vào bảng conversations (tiếp tục)
INSERT INTO conversations (conversation_name)
VALUES
('Conversation 11'),
('Conversation 12'),
('Conversation 13'),
('Conversation 14'),
('Conversation 15'),
('Conversation 16'),
('Conversation 17'),
('Conversation 18'),
('Conversation 19'),
('Conversation 20');

-- Chèn dữ liệu vào bảng messages (tiếp tục)
INSERT INTO messages (sender_id, receiver_id, content, conversation_id, message_parent_id, is_read)
VALUES
(2, 3, 'Message 3 in conversation 1', 1, NULL, FALSE),
(3, 2, 'Message 4 in conversation 1', 1, NULL, FALSE),
(4, 5, 'Message 3 in conversation 2', 2, NULL, FALSE),
(5, 4, 'Message 4 in conversation 2', 2, NULL, FALSE),
(6, 7, 'Message 3 in conversation 3', 3, NULL, FALSE),
(7, 6, 'Message 4 in conversation 3', 3, NULL, FALSE),
(8, 9, 'Message 3 in conversation 4', 4, NULL, FALSE),
(9, 8, 'Message 4 in conversation 4', 4, NULL, FALSE),
(10, 1, 'Message 3 in conversation 5', 5, NULL, FALSE),
(1, 10, 'Message 4 in conversation 5', 5, NULL, FALSE);

-- Chèn dữ liệu vào bảng pages (tiếp tục)
INSERT INTO pages (page_name, content, privacy)
VALUES
('Page 11', 'Content of page 11', 'public'),
('Page 12', 'Content of page 12', 'private'),
('Page 13', 'Content of page 13', 'public'),
('Page 14', 'Content of page 14', 'private'),
('Page 15', 'Content of page 15', 'private'),
('Page 16', 'Content of page 16', 'public'),
('Page 17', 'Content of page 17', 'private'),
('Page 18', 'Content of page 18', 'public'),
('Page 19', 'Content of page 19', 'private'),
('Page 20', 'Content of page 20', 'private');

-- Chèn dữ liệu vào bảng likes (tiếp tục)
INSERT INTO likes (page_id, user_id)
VALUES
(2, 4),
(3, 5),
(4, 6),
(5, 7),
(6, 8),
(7, 9),
(8, 10),
(9, 1),
(10, 2),
(1, 3);



-- Chèn dữ liệu vào bảng roles (tiếp tục)
INSERT INTO roles (name, descriptions)
VALUES
('Viewer', 'Role for viewers'),
('Subscriber', 'Role for subscribers'),
('Manager', 'Role for managers'),
('Editor', 'Role for editors'),
('Contributor', 'Role for contributors'),
('Guest', 'Role for guests'),
('Tester', 'Role for testers'),
('Developer', 'Role for developers'),
('Author', 'Role for authors'),
('Moderator', 'Role for moderators');

-- Chèn dữ liệu vào bảng users (tiếp tục)
INSERT INTO users (username, email, password, fullname, gender, birthdate, avatar, major, nickname, phone_number, company, address, school, friend_id)
VALUES
('user11', 'user11@example.com', 'password11', 'User Eleven', 'Male', '1993-11-11', NULL, 'Linguistics', 'U11', '1234567801', 'Company K', 'Address K', 'School K', NULL),
('user12', 'user12@example.com', 'password12', 'User Twelve', 'Female', '1992-12-12', NULL, 'History', 'U12', '2345678912', 'Company L', 'Address L', 'School L', NULL),
('user13', 'user13@example.com', 'password13', 'User Thirteen', 'Male', '1991-01-13', NULL, 'Geography', 'U13', '3456789123', 'Company M', 'Address M', 'School M', NULL),
('user14', 'user14@example.com', 'password14', 'User Fourteen', 'Female', '1990-02-14', NULL, 'Philosophy', 'U14', '4567891234', 'Company N', 'Address N', 'School N', NULL),
('user15', 'user15@example.com', 'password15', 'User Fifteen', 'Male', '1989-03-15', NULL, 'Literature', 'U15', '5678912345', 'Company O', 'Address O', 'School O', NULL),
('user16', 'user16@example.com', 'password16', 'User Sixteen', 'Female', '1988-04-16', NULL, 'Music', 'U16', '6789123456', 'Company P', 'Address P', 'School P', NULL),
('user17', 'user17@example.com', 'password17', 'User Seventeen', 'Male', '1987-05-17', NULL, 'Drama', 'U17', '7891234567', 'Company Q', 'Address Q', 'School Q', NULL),
('user18', 'user18@example.com', 'password18', 'User Eighteen', 'Female', '1986-06-18', NULL, 'Fine Arts', 'U18', '8912345678', 'Company R', 'Address R', 'School R', NULL),
('user19', 'user19@example.com', 'password19', 'User Nineteen', 'Male', '1985-07-19', NULL, 'Visual Arts', 'U19', '9123456789', 'Company S', 'Address S', 'School S', NULL),
('user20', 'user20@example.com', 'password20', 'User Twenty', 'Female', '1984-08-20', NULL, 'Performing Arts', 'U20', '1234567890', 'Company T', 'Address T', 'School T', NULL);

-- Chèn dữ liệu vào bảng refresh_token (tiếp tục)
INSERT INTO refresh_token (user_id, token, expiry_date)
VALUES
(11, 'token11', '2024-03-27 12:00:00'),
(12, 'token12', '2024-03-27 12:00:00'),
(13, 'token13', '2024-03-27 12:00:00'),
(14, 'token14', '2024-03-27 12:00:00'),
(15, 'token15', '2024-03-27 12:00:00'),
(16, 'token16', '2024-03-27 12:00:00'),
(17, 'token17', '2024-03-27 12:00:00'),
(18, 'token18', '2024-03-27 12:00:00'),
(19, 'token19', '2024-03-27 12:00:00'),
(20, 'token20', '2024-03-27 12:00:00');


-- Chèn dữ liệu vào bảng comments (tiếp tục)
INSERT INTO comments (content, post_id, user_id, parent_comment)
VALUES
('Comment 3 on post 3', 3, 4, NULL),
('Reply to comment 1 on post 2', 2, 2, 6),
('Comment 2 on post 4', 4, 3, NULL),
('Comment 1 on post 5', 5, 4, NULL),
('Comment 3 on post 5', 5, 5, NULL),
('Comment 2 on post 6', 6, 6, NULL),
('Comment 1 on post 7', 7, 7, NULL),
('Comment 2 on post 8', 8, 8, NULL),
('Reply to comment 3 on post 3', 3, 1, 11),
('Comment 1 on post 9', 9, 9, NULL);

-- Chèn dữ liệu vào bảng messages (tiếp tục)
INSERT INTO messages (sender_id, receiver_id, content, conversation_id, message_parent_id, is_read)
VALUES
(2, 1, 'Message 1 in conversation 6', 6, NULL, FALSE),
(3, 1, 'Message 2 in conversation 6', 6, NULL, FALSE),
(4, 1, 'Message 1 in conversation 7', 7, NULL, FALSE),
(5, 1, 'Message 2 in conversation 7', 7, NULL, FALSE),
(6, 1, 'Message 1 in conversation 8', 8, NULL, FALSE),
(7, 1, 'Message 2 in conversation 8', 8, NULL, FALSE),
(8, 1, 'Message 1 in conversation 9', 9, NULL, FALSE),
(9, 1, 'Message 2 in conversation 9', 9, NULL, FALSE),
(10, 1, 'Message 1 in conversation 10', 10, NULL, FALSE),
(1, 1, 'Message 2 in conversation 10', 10, NULL, FALSE);

-- Chèn dữ liệu vào bảng pages (tiếp tục)
INSERT INTO pages (page_name, content, privacy)
VALUES
('Page 21', 'Content of page 21', 'public'),
('Page 22', 'Content of page 22', 'public'),
('Page 23', 'Content of page 23', 'public'),
('Page 24', 'Content of page 24', 'public'),
('Page 25', 'Content of page 25', 'public'),
('Page 26', 'Content of page 26', 'public'),
('Page 27', 'Content of page 27', 'public'),
('Page 28', 'Content of page 28', 'public'),
('Page 29', 'Content of page 29', 'public'),
('Page 30', 'Content of page 30', 'public');

-- Chèn dữ liệu vào bảng likes (tiếp tục)
INSERT INTO likes (page_id, user_id)
VALUES
(11, 5),
(12, 6),
(13, 7),
(14, 8),
(15, 9),
(16, 10),
(17, 1),
(18, 2),
(19, 3),
(20, 4);


-- Chèn dữ liệu vào bảng likes
INSERT INTO likes (page_id, user_id)
VALUES
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8),
(8, 9),
(9, 10),
(10, 1);

-- Chèn dữ liệu vào bảng likes (tiếp tục)
INSERT INTO likes (page_id, user_id, message_id, comment_id, post_id)
VALUES
(21, 1, 1, NULL, NULL),  -- User 1 likes Message 1
(22, 2, 2, NULL, NULL),  -- User 2 likes Message 2
(23, 3, 3, NULL, NULL),  -- User 3 likes Message 3
(24, 4, 4, NULL, NULL),  -- User 4 likes Message 4
(25, 5, 5, NULL, NULL),  -- User 5 likes Message 5
(26, 6, 6, NULL, NULL),  -- User 6 likes Message 6
(27, 7, 7, NULL, NULL),  -- User 7 likes Message 7
(28, 8, 8, NULL, NULL),  -- User 8 likes Message 8
(29, 9, 9, NULL, NULL),  -- User 9 likes Message 9
(30, 10, 10, NULL, NULL);  -- User 10 likes Message 10

-- Chèn dữ liệu vào bảng likes
INSERT INTO likes (user_id, post_id, comment_id, message_id)
VALUES
(1, 1, NULL, NULL),   -- User 1 likes Post 1
(2, NULL, 1, NULL),   -- User 2 likes Comment 1
(3, NULL, NULL, 1),   -- User 3 likes Message 1
(4, 2, NULL, NULL),   -- User 4 likes Post 2
(5, NULL, 2, NULL),   -- User 5 likes Comment 2
(6, NULL, NULL, 2),   -- User 6 likes Message 2
(7, 3, NULL, NULL),   -- User 7 likes Post 3
(8, NULL, 3, NULL),   -- User 8 likes Comment 3
(9, NULL, NULL, 3),   -- User 9 likes Message 3
(10, 4, NULL, NULL);  -- User 10 likes Post 4

-- Chèn dữ liệu vào bảng files
INSERT INTO files (post_id, message_id, page_id, url_file)
VALUES
(1, NULL, NULL, 'url_of_file_for_post_1'),
(2, NULL, NULL, 'url_of_file_for_post_2'),
(NULL, 1, NULL, 'url_of_file_for_message_1'),
(NULL, 2, NULL, 'url_of_file_for_message_2'),
(NULL, NULL, 1, 'url_of_file_for_page_1'),
(NULL, NULL, 2, 'url_of_file_for_page_2'),
(3, NULL, NULL, 'url_of_file_for_post_3'),
(NULL, 3, NULL, 'url_of_file_for_message_3'),
(NULL, NULL, 3, 'url_of_file_for_page_3'),
(4, NULL, NULL, 'url_of_file_for_post_4');




select * from social_media.likes;
select * from social_media.comments;
select * from social_media.conversations;

select * from social_media.files;

select * from social_media.messages;

select * from social_media.pages;

select * from social_media.posts;
select * from social_media.refresh_token;
select * from social_media.roles;
select * from social_media.user_conversation;
select * from social_media.user_role;
select * from social_media.users;






