INSERT INTO dog ("name", "image") VALUES
('Spot', 'https://cdn.britannica.com/47/236047-050-F06BFC5E/Dalmatian-dog.jpg'),
('Scooter', 'https://media-be.chewy.com/wp-content/uploads/2021/04/15160416/Border-Collie_Featured-Image-1024x615.jpg');

INSERT INTO dogbreed ("name") VALUES
('Dalmation'),
('Labrador Retriever'),
('Border Collie');

INSERT INTO dogbreed_dog ("breed_id", "pet_id") VALUES
(1,1),
(2,1),
(3,2);

INSERT INTO cat ("name", "image") VALUES
('Daisy', 'https://www.catbreedslist.com/uploads/cat-pictures/american-shorthair-2.jpg');

INSERT INTO catbreed ("name") VALUES
('American Shorthair');

INSERT INTO catbreed_cat ("breed_id", "pet_id") VALUES
(1,1); 

INSERT INTO fishspecies ("common_name") VALUES
('Angel');

INSERT INTO fish ("name", "species_id", "image") VALUES
('Angie', 1, 'https://media-be.chewy.com/wp-content/uploads/2019/07/18135520/Pterophyllum-scalare-angelfish-1024x576.jpg');