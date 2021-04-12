INSERT INTO USER (id, email, lastname, name, password, phone, username, speciality) VALUES
(1, 'user@email.com', 'useris','useriukas', '{bcrypt}$2y$12$y8uY.LDwuib2e.CFpoEF7.lWcG2P.n33o0jXBBUCc4pykroZn0RvS', '+37060090000', 'user', 'specialist1'),
(2, 'admin@email.com', 'adminas', 'adminukas', '{bcrypt}$2y$12$6C5T4j7HlR8CaokuYbtvMuKU5GAHJxVmq7v9oQonieq5jTAtEiRuG', '+37069099999', 'admin', 'admin');

INSERT INTO ROLE (id, role_name) VALUES
(1, 'USER'),
(2, 'ADMIN');

INSERT INTO USER_ROLES (user_id, role_id) VALUES
(1, 1),
(2, 2),
(2, 1);