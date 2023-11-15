CREATE USER stock SUPERUSER LOGIN PASSWORD 'stock';
CREATE DATABASE stock WITH OWNER stock;
grant all privileges on stock to stock;
CREATE TABLE MAGASIN(
    idMagasin   SERIAL primary key,
    nomMagasin  VARCHAR(30)
);        
INSERT INTO MAGASIN(nomMagasin) VALUES ('M1'),('M2');                 
CREATE TABLE ARTICLE(
    idArticle   SERIAL primary key,
    nomArtticle     VARCHAR(30),
    reference       VARCHAR(30),
    typeArticle     int -- 0 Lifo / 1 fifo --
);    
INSERT INTO Article (nomArtticle,reference,typearticle) VALUES ('Vary Mena','V012',0),('Vary Fotsy','V011',1),('Vary LUX','V0112',1);
CREATE TABLE UNITE(
    idUnite     serial primary key,
    nomUnite    VARCHAR(30)
);        
INSERT INTO UNITE(nomunite) VALUES ('KILO'),('Tonnes'); 
CREATE TABLE ENTRE(
    idEntrer    serial primary key,
    idArticle   int references article (idArticle),
    prixUnitaire    double precision,
    quantite        double precision,
    idMagasin       int references magasin (idMagasin),
    unite           int references unite (idunite),
    dateEntre       date
);
INSERT INTO ENTRE (idArticle, prixUnitaire, quantite, idMagasin, unite, dateEntre) VALUES 
(2,2000.0,1000,1,1,'2020-09-01'),
(2,2300.0,500,1,1,'2021-11-01'),
(2,2500.0,200,1,1,'2021-12-03'),

(1,1500.0,500,1,1,'2021-11-01'),
(1,1700.0,1400,1,1,'2021-11-03'),
(1,2000.0,1000,1,1,'2021-11-05');

CREATE TABLE SORTIE(
    idSortie    serial primary key,
    idArticle   int references article (idArticle),
    quantite        double precision,
    idMagasin       int references magasin (idMagasin),
    unite           int references unite (idunite),
    dateSortie       date,
    idEntrer        int references entre (identrer)
);
INSERT INTO SORTIE (idArticle, quantite, idMagasin, unite, dateSortie) VALUES 
(1,20, 2, 1, '2023-11-15'),(2, 30,1,1, '2023-11-06');

-- Script
Create  or replace view entrer as
select e.identrer, e.idarticle,art.nomartticle,art.reference,e.prixunitaire,e.quantite,m.nommagasin,e.idmagasin,u.nomunite,dateentre from entrerActif e 
        join article art on art.idarticle = e.idarticle 
        join magasin m on m.idmagasin= e.idmagasin 
        join unite u on u.idunite=e.unite; 
        where e.idarticle = 1; 

Create  or replace view entrerActif as
select e.identrer, e.idarticle,e.prixunitaire, (e.quantite-coalesce(s.quantite,0)) quantite,e.idmagasin,e.unite,e.dateentre 
    from entre e left join sorties s on e.identrer = s.identrer;

create view sorties as
select idarticle, sum(quantite) quantite ,idmagasin,unite, identrer from sortie group by identrer,idarticle, idmagasin,unite; 
