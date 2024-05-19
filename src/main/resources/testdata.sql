INSERT INTO dog ("name") VALUES
('Spot'),
('Scooter');

INSERT INTO dogbreed ("name") VALUES
('Dalmation'),
('Labrador Retriever'),
('Border Collie');

INSERT INTO dogbreed_dog ("breed_id", "pet_id") VALUES
(1,1),
(2,1),
(3,2);

INSERT INTO cat ("name") VALUES
('Daisy');

INSERT INTO catbreed ("name") VALUES
('American Shorthair');

INSERT INTO catbreed_cat ("breed_id", "pet_id") VALUES
(1,1); 

INSERT INTO fishspecies ("common_name") VALUES
('Angel');

INSERT INTO fish ("name", "species_id") VALUES
('Angie', 1);