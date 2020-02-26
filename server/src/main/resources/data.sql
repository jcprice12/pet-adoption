INSERT INTO `dog` (`name`) VALUES
('Spot'),
('Scooter');

INSERT INTO `dogbreed` (`name`) VALUES
('Dalmation'),
('Labrador Retriever'),
('Border Collie');

INSERT INTO `dogbreed_dog` (`breed_id`, `dog_id`) VALUES
(1,1),
(2,1),
(3,2);

INSERT INTO `cat` (`name`) VALUES
('Daisy');

INSERT INTO `catbreed` (`name`) VALUES
('American Shorthair');

INSERT INTO `catbreed_cat` (`breed_id`, `cat_id`) VALUES
(1,1); 
