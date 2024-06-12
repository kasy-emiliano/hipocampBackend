CREATE DATABASE elearning
    WITH ENCODING = 'UTF8'
    LC_COLLATE = 'French_Madagascar.1252'
    LC_CTYPE = 'French_Madagascar.1252';
 
\c elearning;


CREATE TABLE IF NOT EXISTS public.Admin
(
    id serial primary key,
    email varchar,
    mdp varchar(20),
    nom varchar

);

insert into Admin(email,mdp,nom)values('rand@gmail.com','0000','Admin');


CREATE TABLE IF NOT EXISTS public.civilite
(

    idcivilite serial primary key,
    nom varchar
);


insert into civilite(nom)values('Docteur');
insert into civilite(nom)values('Professeur');
insert into civilite(nom)values('Madame');
insert into civilite(nom)values('Monsieur');
insert into civilite(nom)values('Mademoiselle');


CREATE TABLE IF NOT EXISTS public.Profession
(

   idProfession serial primary key,
    nom varchar
);



insert into Profession(nom)values('Medecin (generaliste ou specialiste)');
insert into Profession(nom)values('Etudiant (e)');
insert into Profession(nom)values('Agent communautaire');
insert into Profession(nom)values('Infirmier(e)');
insert into Profession(nom)values('Pharmacien (ne)');
insert into Profession(nom)values('Dentiste');
insert into Profession(nom)values('Orthophoniste');
insert into Profession(nom)values('Psychologue');
insert into Profession(nom)values('Kinesitherapeute / physiotherapeute');
insert into Profession(nom)values('Radiologue');
insert into Profession(nom)values('Dieteticien / nutritionniste');
insert into Profession(nom)values('Technicien de laboratoire médical');
insert into Profession(nom)values('Technicien en pharmacie');
insert into Profession(nom)values('Podologue');
insert into Profession(nom)values('Medecin legiste');
insert into Profession(nom)values('Autres');



CREATE TABLE IF NOT EXISTS public.modeDexercice
(

   idmodeDexercice serial primary key,
    nom varchar
);
insert into modeDexercice(nom)values('Public');
insert into modeDexercice(nom)values('Prive');
insert into modeDexercice(nom)values('Libéral');
insert into modeDexercice(nom)values('Exercice mixte');
insert into modeDexercice(nom)values('Salarie');


CREATE TABLE IF NOT EXISTS public.Langues
(

   idLangues serial primary key,
    nom varchar
);
insert into Langues(nom)values('Français');
insert into Langues(nom)values('Anglais');
insert into Langues(nom)values('Malagasy');


CREATE TABLE IF NOT EXISTS public.TypesAcces
(

   idTypesAcces serial primary key,
    nom varchar
);
insert into TypesAcces(nom)values('Gratuit');
insert into TypesAcces(nom)values('Payant');
insert into TypesAcces(nom)values('Privé');



CREATE TABLE IF NOT EXISTS public.Categorie
(

   idCategorie serial primary key,
    nom varchar
);

insert into Categorie(nom)values('COVID-19');
insert into Categorie(nom)values('Rapport Mensuel d''Activités (RMA)');
insert into Categorie(nom)values('Pharmacovigilance');
insert into Categorie(nom)values('Vaccination');
insert into Categorie(nom)values('Maladies tropicales');
insert into Categorie(nom)values('Santé communautaire');
insert into Categorie(nom)values('DHIS2');
insert into Categorie(nom)values('Santé et bien être');
insert into Categorie(nom)values('Maladies sexuellement transmissibles (MST)');
insert into Categorie(nom)values('Planning familial');
insert into Categorie(nom)values('Hygiène');
insert into Categorie(nom)values('Maladies infectieuses');
insert into Categorie(nom)values('Santé sexuelle');
insert into Categorie(nom)values('Santé environnementale');
insert into Categorie(nom)values('Gestion');
insert into Categorie(nom)values('Développement personnel');
insert into Categorie(nom)values('Urgences');
insert into Categorie(nom)values('Communication / marketing');
insert into Categorie(nom)values('Management'); 
insert into Categorie(nom)values('Soins infirmiers');
insert into Categorie(nom)values('Télémédecine'); 







CREATE TABLE IF NOT EXISTS public.Apprenant
(
    idApprenant serial primary key,
    Nom varchar,
    Prenom varchar,
    email varchar,
    mdp varchar,
    Profession integer,
    civilite integer,
    modeDexercice integer,
    numero varchar,
    datenaissance Date,
    token varchar,
    etatCompte int,
    dateDajout date,
    foreign key (civilite) references civilite(idcivilite),
    foreign key (Profession) references Profession(idProfession),
    foreign key (modeDexercice) references modeDexercice(idmodeDexercice)
);

insert into Apprenant(nom,Prenom,email,mdp,Profession,civilite,modeDexercice,numero,token,etatCompte,dateDajout)values('Cedric','Kasy','cedrickasy10@gmail.com','cedi',2,4,1,'0323212321','abc',1,'2024-04-06');

INSERT INTO Apprenant(nom, Prenom, email, mdp, Profession, civilite, modeDexercice, numero, token, etatCompte, dateDajout) VALUES
('Alice', 'Dupont', 'alice.dupont@example.com', 'alice123', 3, 2, 1, '0323212322', 'def', 1, '2024-04-07'),
('Bob', 'Martin', 'bob.martin@example.com', 'bob321', 1, 1, 2, '0323212323', 'ghi', 1, '2024-04-08'),
('Claire', 'Durand', 'claire.durand@example.com', 'claire2024', 4, 2, 3, '0323212324', 'jkl', 1, '2024-04-09'),
('David', 'Smith', 'david.smith@example.com', 'davids', 2, 4, 1, '0323212325', 'mno', 1, '2024-04-10'),
('Emma', 'Johnson', 'emma.johnson@example.com', 'emma456', 3, 1, 2, '0323212326', 'pqr', 1, '2024-04-11'),
('Frank', 'Brown', 'frank.brown@example.com', 'frank789', 1, 2, 3, '0323212327', 'stu', 1, '2024-04-12'),
('Grace', 'Lee', 'grace.lee@example.com', 'grace101', 4, 1, 1, '0323212328', 'vwx', 1, '2024-04-13'),
('Henry', 'Wilson', 'henry.wilson@example.com', 'henry123', 2, 3, 2, '0323212329', 'yz', 1, '2024-04-14'),
('Ivy', 'Moore', 'ivy.moore@example.com', 'ivy2024', 3, 2, 1, '0323212330', 'abc123', 1, '2024-04-15'),
('Jack', 'Taylor', 'jack.taylor@example.com', 'jack321', 1, 4, 3, '0323212331', 'def456', 1, '2024-04-16'),
('Kelly', 'Anderson', 'kelly.anderson@example.com', 'kelly654', 4, 1, 2, '0323212332', 'ghi789', 1, '2024-04-17'),
('Luke', 'Thomas', 'luke.thomas@example.com', 'luke987', 2, 3, 1, '0323212333', 'jkl101', 1, '2024-04-18'),
('Mia', 'Jackson', 'mia.jackson@example.com', 'mia112', 3, 2, 2, '0323212334', 'mno112', 1, '2024-04-19'),
('Nick', 'White', 'nick.white@example.com', 'nick123', 1, 4, 3, '0323212335', 'pqr123', 1, '2024-04-20'),
('Olivia', 'Harris', 'olivia.harris@example.com', 'olivia456', 4, 2, 1, '0323212336', 'stu456', 1, '2024-04-21'),
('Paul', 'Martin', 'paul.martin@example.com', 'paul789', 2, 1, 2, '0323212337', 'vwx789', 1, '2024-04-22'),
('Quinn', 'Garcia', 'quinn.garcia@example.com', 'quinn101', 3, 4, 3, '0323212338', 'yz101', 1, '2024-04-23'),
('Rose', 'Martinez', 'rose.martinez@example.com', 'rose2024', 1, 2, 1, '0323212339', 'abc456', 1, '2024-04-24'),
('Sam', 'Robinson', 'sam.robinson@example.com', 'sam321', 4, 1, 2, '0323212340', 'def789', 1, '2024-04-25');



CREATE TABLE IF NOT EXISTS public.DemandeAdhesion
(

    idDemande serial primary key,
    Ajout Timestamp,
    Organisme varchar,
    Nom varchar,
    Prenom varchar,
    ville varchar,
    email varchar,
    numero varchar,
    objet varchar,
    message varchar,
    token varchar,
    etat int


);

CREATE TABLE IF NOT EXISTS public.Formateur
(

    idFormateur serial primary key,
    Nom varchar,
    Prenom varchar,
    email varchar,
    mdp varchar,
    NomOrgannisme varchar,
    ville varchar,
    civilite integer,
    Profession integer,
    modeDexercice integer,
    bio varchar,
    numero varchar,
    datenaissance Date,
    facebook varchar,
    linkedin varchar,
    token varchar,
    etatCompte int,
    Pdp varchar,
    Pdc varchar,
    dateDajout date,
    phrasecertificat varchar,
    nomespace varchar,
    logo varchar,
    couleurPrincipale varchar,
    couleurArrierePlan varchar,
    CouleurTitre varchar,
    couleurText varchar,
    couleurBouton varchar,
    couleurtextBouton varchar,
    foreign key (civilite) references civilite(idcivilite),
    foreign key (Profession) references Profession(idProfession),
    foreign key (modeDexercice) references modeDexercice(idmodeDexercice)
);

insert into Formateur(nom,Prenom,email,mdp,NomOrgannisme,ville,civilite,Profession,modeDexercice,bio,numero,facebook,linkedin,token,etatCompte,Pdp)values('Kasy','Emiliano','kasy@gmail.com','kasy','Kaiamba','Tamatave',2,10,1,'Professeur Kasy','0343212321','Kasy Jr','KasyJr','def',1,'sary.jpg');

CREATE TABLE IF NOT EXISTS public.unite
(

   idUnite serial primary key,
    nom varchar
);
insert into unite(nom)values('Heures');
insert into unite(nom)values('Jours');
insert into unite(nom)values('Semaines');
insert into unite(nom)values('Années');

CREATE TABLE IF NOT EXISTS public.UnitePrix
(

   idUnitePrix serial primary key,
    nom varchar
);
insert into UnitePrix(nom)values('Ariary');


CREATE TABLE IF NOT EXISTS public.FuseauxHoraire
(

   idFuseauxHoraire serial primary key,
    nom varchar
);
insert into FuseauxHoraire(nom)values('naerobie');




CREATE TABLE public.Formation
(

  idFormation serial primary key,
  idFormateur integer,
  idCategorie integer,
  TypesAcces integer,
  Langues integer,
  Titre varchar,
  duree varchar,
  unite integer,
  resumer Text,
  pdc varchar,
  token varchar,
  etat integer,
  prix double precision,
  dateDajout date,
  devalidation date,
  dedemande date,
  etatPublication integer,
  depenseformation double precision default 200000,
    foreign key (idFormateur) references Formateur(idFormateur),
    foreign key (unite) references unite(idUnite),
    foreign key (idCategorie) references Categorie(idCategorie),
    foreign key (TypesAcces) references TypesAcces(idTypesAcces),
    foreign key (Langues) references Langues(idLangues)

);

insert into formation (idFormateur,idCategorie,TypesAcces,Langues,titre,duree,unite,resumer,pdc,token,etat,prix,dateDajout,devalidation,dedemande) values(1,1,1,1,'Solution Contre le Covid-19','2',3,'Cette formation consiste a vous sensibiliser contre le covid-19,Ici vous pouvez trouvez une plusieurs solutions contre ce virus.','pdc.jpg','ghi',1,500000,'10-01-2024','16-01-2024','12-01-2024');


CREATE TABLE public.Chapitres
(
idChapitres serial primary key,
idFormation integer,
titre varchar

);


CREATE TABLE public.SousChapitres
(
idSousChapitres serial primary key,
idChapitres integer,
titre varchar
);

CREATE TABLE public.ContenuSousChapitres(
 idContenuSousChapitres serial primary key,
 idSousChapitres integer,
  Contenu varchar,
  legende varchar,
  typa varchar
);















CREATE TABLE public.Zoom
(
idZoom serial primary key,
idFormation integer,
titre varchar,
daty Date,
HeureDeb varchar,
minuteDeb varchar,
HeureFin varchar,
minuteFin varchar,
FuseauxHoraire integer,
lien varchar
);



CREATE TABLE public.Quiz
(
idQuiz serial primary key,
idFormation integer,
Titre varchar
);

CREATE TABLE IF NOT EXISTS public.TypeQuestion
(

   idTypeQuestion serial primary key,
    nom varchar
);
insert into TypeQuestion(nom)values('choix unique');
insert into TypeQuestion(nom)values('choix multiple');

CREATE TABLE public.QuestionQuiz
(
idQuestionQuiz serial primary key,
idQuiz integer,
idTypeQuestion integer,
Question varchar
); 


CREATE TABLE public.ReponseQuiz
(
idReponseQuiz serial primary key,
idQuestion integer,
Reponse varchar,
note double precision
);  


CREATE TABLE public.inscritFormation(
idinscritFormation serial primary key,
idApprenant integer,
idFormation integer,
dateDajout date,
droitPaye double precision,
foreign key (idApprenant) references Apprenant(idApprenant),
foreign key (idFormation) references Formation(idFormation)
);


CREATE TABLE public.mouvementChapitres
(
idmouvementChapitres serial primary key,
idApprenant integer,
idFormation integer,
idSousChapitres integer,
foreign key (idApprenant) references Apprenant(idApprenant),
foreign key (idFormation) references Formation(idFormation),
foreign key (idSousChapitres) references SousChapitres(idSousChapitres)
);









 


CREATE TABLE public.mouvementZoom
(
idmouvementZoom serial primary key,
idApprenant integer,
idFormation integer,
idZoom integer,
foreign key (idApprenant) references Apprenant(idApprenant),
foreign key (idFormation) references Formation(idFormation),
foreign key (idZoom) references Zoom(idZoom)
);

CREATE TABLE public.mouvementQuiz
(
idmouvementQuiz serial primary key,
tentative integer,
idApprenant integer,
idQuiz integer,
idReponseQuiz integer,
foreign key (idApprenant) references Apprenant(idApprenant),
foreign key (idQuiz) references Quiz(idQuiz), 
foreign key (idReponseQuiz) references ReponseQuiz(idReponseQuiz)
);



CREATE TABLE commentaire (
    idcommentaire serial primary key,
    idFormation int,
    idApprenant int,
    idFormateur int,
    commentaire Text,
    dateCommentaire date default CURRENT_DATE,
    foreign key (idFormation) references formation(idFormation),
    foreign key (idApprenant) references Apprenant(idApprenant),
    foreign key (idFormateur) references Formateur(idFormateur)
);

    --si c'est apprenant qui commente
    insert into commentaire(idFormation,idApprenant,commentaire) values(1,1,'formation parfait');
    --si c'est formateur qui commente
    insert into commentaire(idFormation,idFormateur,commentaire) values(4,3,'cette formation vous offre plein des oportunité');




CREATE VIEW FormationCommentaire AS
SELECT
    commentaire.idcommentaire,
    commentaire.idFormation,
    commentaire.idApprenant,
    commentaire.idFormateur,
    commentaire.commentaire,
    commentaire.dateCommentaire,
    formateur.Nom AS NomFormateur,
    formateur.Prenom AS PrenomFormateur,
    apprenant.Nom AS NomApprenant,
    apprenant.Prenom AS PrenomApprenant
FROM
    commentaire
LEFT JOIN
    Formation ON commentaire.idFormation = Formation.idFormation
LEFT JOIN
    Formateur ON commentaire.idFormateur = Formateur.idFormateur
LEFT JOIN
    Apprenant ON commentaire.idApprenant = Apprenant.idApprenant;

create table ReponseCommentaire(
    idReponse serial primary key,
    idcommentaire int REFERENCES commentaire(idcommentaire) ON DELETE CASCADE,
    idApprenant int,
    idFormateur int,
    ReponseCommentaire Text,
    dateReponseCommentaire date default CURRENT_DATE,
    foreign key (idApprenant) references Apprenant(idApprenant),
    foreign key (idFormateur) references Formateur(idFormateur)
);
    --si c'est apprenant qui repond
        insert into ReponseCommentaire(idcommentaire,idApprenant,ReponseCommentaire) values(2,1,'oui vraiment beaucoup');

    --si c'est formateur qui repond
    insert into ReponseCommentaire(idcommentaire,idFormateur,ReponseCommentaire) values(2,3,'salut  tres bien');

CREATE VIEW FormationReponseCommentaire AS
SELECT
    ReponseCommentaire.idReponse,
    ReponseCommentaire.idcommentaire,
    ReponseCommentaire.idApprenant,
    ReponseCommentaire.idFormateur,
    ReponseCommentaire.ReponseCommentaire,
    ReponseCommentaire.dateReponseCommentaire,
    formateur.Nom AS NomFormateur,
    formateur.Prenom AS PrenomFormateur,
    apprenant.Nom AS NomApprenant,
    apprenant.Prenom AS PrenomApprenant
FROM
    ReponseCommentaire
LEFT JOIN
    commentaire ON ReponseCommentaire.idcommentaire = commentaire.idcommentaire
LEFT JOIN
    Formateur ON ReponseCommentaire.idFormateur = Formateur.idFormateur
LEFT JOIN
    Apprenant ON ReponseCommentaire.idApprenant = Apprenant.idApprenant;


CREATE TABLE NoteFormation (
    idNote serial primary key,
    idFormation INT,
    idApprenant INT,
    note INT,
    dateNote date default CURRENT_DATE,
    FOREIGN KEY (idFormation) REFERENCES Formation(idFormation),
    FOREIGN KEY (idApprenant) REFERENCES Apprenant(idApprenant)
);

insert into NoteFormation(idFormation,idApprenant,note) values(5,1,4);

SELECT AVG(note) AS moyenne_note FROM NoteFormation WHERE idformation = 5;




CREATE TABLE public.publicite
(
idPublicite serial primary key,
NomOrganisme varchar,
sary varchar,
email varchar,
contact varchar,
dateDebut timestamp default now(),
dateFin timestamp default now(),
montantParJours double precision default 5000,
titre varchar,
resumer varchar,
lien varchar
);

INSERT INTO publicite (NomOrganisme, sary, email, contact, dateDebut, dateFin, titre, resumer, lien)
VALUES 
('Réveillon du Nouvel An', 'reveillonnouvelan.jpg', 'contact@reveillonnouvelan.com', '+1234567809', '2023-12-31 20:00:00', '2024-01-01 20:00:00', 'Nouvelle Année, Nouveaux Départs', 'Célébrez le passage à la nouvelle année avec une soirée mémorable comprenant dîner, danse et feu artifice.', 'https://www.reveillonnouvelan.com');
('Marché de Noel', 'marchenoel.jpg', 'info@marchenoel.com', '+3216549870', '2023-12-01 10:00:00', '2023-12-04 21:00:00', 'Féérie de Noel', 'Venez découvrir des produits artisanaux, des décorations festives et des spécialités culinaires au marché de Noel.', 'https://www.marchenoel.com'),

('Festival des Lumières', 'festival_lumieres.jpg', 'contact@festivallumieres.com', '+1987654321', '2023-12-08 18:00:00', '2023-12-11 23:00:00', 'eclats de Lumière', 'Admirez des installations lumineuses spectaculaires et profitez animations et de spectacles en soirée.', 'https://www.festivallumieres.com'),

('Concert de Noel', 'concert_noel.jpg', 'info@concertnoel.com', '+1237896540', '2023-12-15 19:00:00', '2023-12-16 21:30:00', 'Mélodies Festives', 'Venez assister à un concert de Noël avec des performances de chorales et orchestres interprétant des classiques de Noel.', 'https://www.concertnoel.com'),

('Réveillon du Nouvel An', 'reveillonnouvelan.jpg', 'contact@reveillonnouvelan.com', '+1234567809', '2023-12-31 20:00:00', '2024-01-01 20:00:00', 'Nouvelle Année, Nouveaux Départs', 'Célébrez le passage à la nouvelle année avec une soirée mémorable comprenant dîner, danse et feu artifice.', 'https://www.reveillonnouvelan.com');





    INSERT INTO publicite (NomOrganisme, sary, email, contact, dateDebut, dateFin, duree, montantParJours,titre,resumer,lien)
    VALUES 
    ('Google', 'uploads/mm.PNG', 'contact@google.com', '+1357924680', '2024-03-04 12:00:00', '2024-03-06 12:00:00', 7, 55.00,'sélo ou duo','une maniere de voir les choses entre deux ou tout seul','https://www.star.mg/historique');

CREATE VIEW JourPublicite AS
SELECT *,DATE_PART('day', datefin - datedebut) AS jour
FROM publicite;

CREATE VIEW jourxmontant AS
 select idPublicite,nomorganisme,titre,datedebut,(jour*montantparjours ) as jourxmontant ,EXTRACT(MONTH FROM datedebut)AS mois,EXTRACT(YEAR FROM datedebut) AS annee  from JourPublicite;


 create view annee as
select annee from jourxmontant group by annee;  
create view moisAnnee as
select*from annee cross join mois;

 create view tableaubord as
 SELECT 
    jp.idPublicite,
    jp.nomorganisme,
    jp.titre,
    jp.datedebut,
    coalesce(jp.jourxmontant,0) as jourxmontant,
    jp.mois,
    jp.annee,
    ma.id as idmois,
    ma.mois AS nom_mois
FROM 
moisAnnee as ma
 left JOIN 
    jourxmontant jp
   
ON 
    ma.annee=jp.annee  AND 
    ma.id=jp.mois;

**************Par annee******
SELECT
    ma.mois AS nom_mois,
    COALESCE(SUM(tb.jourxmontant), 0) AS totalrevenue
FROM
    moisAnnee ma
LEFT JOIN
    tableaubord tb
ON
    ma.annee = tb.annee AND
    ma.id = tb.mois
WHERE
    ma.annee = 2023
GROUP BY
    ma.mois,
    ma.id
ORDER BY ma.id;

****Somme total revenue******

SELECT
    SUM(totalrevenue) AS somme_totalrevenue
FROM
    (SELECT
        ma.mois AS nom_mois,
        COALESCE(SUM(tb.jourxmontant), 0) AS totalrevenue
    FROM
        moisAnnee ma
    LEFT JOIN
        tableaubord tb
    ON
        ma.annee = tb.annee AND
        ma.id = tb.mois
    WHERE
        ma.annee = 2024
    GROUP BY
        ma.mois, ma.id
    ORDER BY ma.id) AS revenus;

 ************

********************

CREATE VIEW InfoFormateur AS
SELECT
    f.idFormateur, 
    f.Nom, 
    f.Prenom, 
    f.email, 
    f.mdp, 
    f.NomOrgannisme, 
    f.ville, 
    c.nom AS civilite_nom, 
    p.nom AS profession_nom, 
    f.modeDexercice, 
    f.bio, 
    f.numero, 
    f.datenaissance, 
    f.facebook, 
    f.linkedin, 
    f.token, 
    f.etatCompte, 
    f.Pdp, 
    f.Pdc, 
    f.dateDajout 
FROM 
    Formateur f 
    LEFT JOIN civilite c ON f.civilite = c.idcivilite 
    LEFT JOIN Profession p ON f.Profession = p.idProfession;

Select * from formation  join categorie on formation.idcategorie=categorie.idcategorie join typesacces on formation.typesacces=typesacces.idTypesAcces join langues on formation.langues=langues.idLangues join unite on formation.unite=unite.idUnite Where formation.etat=2


CREATE TABLE IF NOT EXISTS public.DeleteCategorie
(

    idDeleteCategorie serial primary key,
    idCategorie integer,
  foreign key (idCategorie) references Categorie(idCategorie)
);

CREATE VIEW MesFormation AS
select
idformation,
idformateur,
typesacces,langues,titre,duree,unite,resumer,pdc,token,etat,prix,datedajout,devalidation,dedemande
 from formation join categorie on formation.idcategorie=categorie.idcategorie join typesacces on formation.typesacces=typesacces.idTypesAcces join langues on formation.langues=langues.idLangues join unite on formation.unite=unite.idUnite;


    

 create table messages (
    idMessage serial primary key,
    idformateur int,
    idApprenant int,
    messages Text,
    date timestamp default now(),
    type int,
    vue int,
    FOREIGN KEY (idFormateur) REFERENCES Formateur(idFormateur),
    FOREIGN KEY (idApprenant) REFERENCES Apprenant(idApprenant)
);


INSERT INTO messages (idformateur, idApprenant, messages) 
VALUES 
(8, 4, 'Bonjour, comment ça va ?'),
(8, 4, 'Bonjour, jespère que vous avez passé une bonne journée ?', '2024-04-07 10:00:01'),
(8, 6, 'Bonjour, je suis disponible pour discuter.', '2024-04-07 10:10:00'),
(8, 6, 'Salut, comment puis-je vous aider ?', '2024-04-07 10:15:00');


insert into messages (idformateur,idApprenant,senderName,receiverName,messages) values(8,4,'bolt','Kasy','salut lesy eh');
insert into messages (idApprenant,senderName,messages) values(4,8'emiliano','salut cedi');
insert into messages (idApprenant,senderName,messages) values(6,'bolt','salut cedi');



 
CREATE or replace VIEW messagePrive AS
SELECT 
    messages.idMessage,
    messages.messages,
    messages.idApprenant, 
    Apprenant.Nom AS Nom_Apprenant, 
    Apprenant.Prenom AS Prenom_Apprenant,
    Apprenant.token as tokenApprenant,
    messages.idFormateur, 
    Formateur.Nom AS Nom_Formateur, 
    Formateur.Prenom AS Prenom_Formateur,
    Formateur.token as token,
    messages.date,
    messages.type,
    messages.vue
FROM 
    messages
LEFT JOIN 
    Apprenant ON messages.idApprenant = Apprenant.idApprenant
LEFT JOIN 
    Formateur ON messages.idFormateur = Formateur.idFormateur;
 


 idapprenant | nom_apprenant | prenom_apprenant | idformateur | nom_formateur | prenom_formateur | idformation | titre_formation
-------------+---------------+------------------+-------------+---------------+------------------+-------------+-----------------
(0 rows)

select*from messages where idformateur=10 and idApprenant=10 ;
select idApprenant,nom_apprenant,prenom_apprenant,max(date) from messagePrive where idformateur=10 group by idApprenant,nom_apprenant,prenom_apprenant;

select idApprenant,max(date) from messagePrive where idformateur=10  group by idApprenant;

select idapprenant from apprenant where token='BYWJffyzQnBDlDfscjdfN1XPyEBM8Zz6ASXI5_IdS3k';

Table "Questions":


create Table Examens(
idExamen serial primary key,
idFormation int,
TitreExamen text,
DateDebutExamen timestamp,
DateFinExamen timestamp,
FOREIGN KEY (idFormation) REFERENCES Formation(idFormation)
);

INSERT INTO Examens (idFormation, TitreExamen, DateDebutExamen, DateFinExamen)
VALUES
    (29, 'Examen de mathématiques avancées', '2024-05-16 15:11:00', '2024-05-16 15:30:00');
    (2, 'Examen de physique quantique', '2024-05-09 22:55:00', '2024-05-09 22:57:00')
    (1, 'Examen de programmation orientée objet', '2024-05-20 10:00:00', '2024-05-20 13:00:00');

SELECT EXTRACT(EPOCH FROM (DateFinExamen - DateDebutExamen)) AS DureeExamen_secondes
FROM Examens
WHERE idExamen = 19;




INSERT INTO Examens (TitreExamen, idFormation)
VALUES ('Examen 1', 17);
       ('Examen de français', '2024-04-20', 1),
       ('Examen de physique', '2024-04-25', 2);

create table QuestionExamen(
idQuestion serial primary key,
idExamen int,
idTypeQuestion int,
Question Text,
FOREIGN KEY (idExamen) REFERENCES Examens(idExamen),
FOREIGN KEY (idTypeQuestion) REFERENCES TypeQuestion(idTypeQuestion)
);

insert into QuestionExamen (idExamen,idTypeQuestion,Question) values(12,1,' autre prenom de cedric est quoi?');
insert into QuestionExamen (idExamen,idTypeQuestion,Question) values(12,1,'Cedric mange quoi?');
insert into QuestionExamen (idExamen,idTypeQuestion,TexteQuestion) values(1,2,'quel sont les coleurs du drapeau malgache?');


create Table ReponsesExamen(
idReponse serial primary key,
idQuestion int,
Reponse Text,
note double precision,
typeReponses double precision,
FOREIGN KEY (idQuestion) REFERENCES QuestionExamen(idQuestion)
);

INSERT INTO ReponsesExamen (Reponse, idQuestion, note) 
VALUES ('Vary', 51, 0),
       ('apango', 51, 1);
       ('Emilio', 50, 0);
       ('Rouge', 3, 1),
       ('Vert', 3, 1);

create table ReponsesApprenant(
idReponseApprenant serial primary key,
idExamen int,
idQuestion int,
idApprenant int,
idReponse int,
reponseLibre text,
FOREIGN KEY (idExamen) REFERENCES Examens(idExamen),
FOREIGN KEY (idApprenant) REFERENCES Apprenant(idApprenant),
FOREIGN KEY (idReponse) REFERENCES ReponsesExamen(idReponse),
FOREIGN KEY (idQuestion) REFERENCES QuestionExamen(idQuestion)
);

insert into ReponsesApprenant(idExamen,idApprenant,idReponse,)values(12,3,115);(12,3,109);  

SELECT ReponsesExamen.idreponse, ReponsesExamen.idquestion, ReponsesExamen.reponse, ReponsesExamen.note
FROM ReponsesExamen
JOIN QuestionExamen ON ReponsesExamen.idquestion = QuestionExamen.idQuestion
WHERE QuestionExamen.idExamen = 69;

/**create Table Questions_Examen(
idExamen int,
idQuestion int,
FOREIGN KEY (idExamen) REFERENCES Examens(idExamen),
FOREIGN KEY (idQuestion) REFERENCES QuestionExamen(idQuestion)
);
insert into Questions_Examen(idExamen,idQuestion) values(1,2);
*/
 

  
 

 

 

SELECT f.idFormation,
       f.Titre AS TitreFormation,
       e.idExamen,
       e.TitreExamen,
       e.DateExamen
FROM Formation f
INNER JOIN Examens e ON f.idFormation = e.idFormation;



CREATE or replace VIEW totalNoteExam AS 
SELECT re.idReponse, re.Reponse, re.idQuestion, re.note,
       qe.idExamen as idExamen, qe.idTypeQuestion, qe.Question
FROM ReponsesExamen re
INNER JOIN QuestionExamen qe ON re.idQuestion = qe.idQuestion;


SELECT
    question.id AS question_id,
    question.text AS question_text,
    GROUP_CONCAT(response.id, ':', response.text) AS responses
FROM
    Examens
JOIN
    question ON exam.id = question.exam_id
LEFT JOIN
    response ON question.id = response.question_id
WHERE
    exam.id = <ID_DE_L_EXAMEN>
GROUP BY
    question.id;


CREATE or replace VIEW QuestionReponse AS 
    SELECT
    q.idQuestion AS idQuestion,
    q.Question AS question,
    q.idExamen as idExamen,
    r.idReponse AS idResponse,
    r.Reponse AS response,
    r.note AS note,
    r.typeReponses
FROM
    QuestionExamen q
LEFT JOIN
    ReponsesExamen r ON q.idQuestion = r.idQuestion;
WHERE
    q.idExamen = 12;

    SELECT q.question_id, q.question_text, r.response_id, r.response_text, r.response_note
FROM QuestionExamen q
JOIN ReponsesExamen r ON q.question_id = r.question_id;
WHERE q.examen_id = 12;



SELECT q.idQuestion AS question_id, q.Question AS question_text,
                       r.idReponse AS response_id, r.Reponse AS response_text, r.note AS response_note 
                       FROM QuestionExamen q  
                       LEFT JOIN ReponsesExamen r ON q.idQuestion = r.idQuestion
                       WHERE q.idExamen = 12;

create table ChekExamen(
    idApprenant int,
    idformation int,
    idExamen int,
FOREIGN KEY (idApprenant) REFERENCES Apprenant(idApprenant),
FOREIGN KEY (idformation) REFERENCES formation(idformation),
FOREIGN KEY (idExamen) REFERENCES examens(idExamen)
);


    CREATE or replace VIEW ResultatExamen AS
    SELECT a.idApprenant,
        a.Nom,
        a.Prenom,
        a.email,
        ra.idreponse,
        ra.idExamen AS idExamen,
        re.Reponse AS ReponseApprenant,
        re.note,
        f.idFormation,
        f.Titre AS TitreFormation,
        e.TitreExamen,
        e.datedebutexamen,
        ft.idformateur,
        ft.nom as NomFormateur,
        ft.prenom as PrenomFormateur,
        ft.nomorgannisme,
        ft.phraseCertificat
    FROM ReponsesApprenant ra
    INNER JOIN ReponsesExamen re ON ra.idReponse = re.idReponse
    INNER JOIN public.Apprenant a ON ra.idApprenant = a.idApprenant
    INNER JOIN Examens e ON ra.idExamen = e.idExamen
    INNER JOIN Formation f ON e.idFormation = f.idFormation
    INNER JOIN Formateur ft ON f.idFormateur = ft.idFormateur;


/*****peut etre liste*****/
SELECT SUM(note) AS noteApprenant,idApprenant,nom,prenom,idFormateur,NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,datedebutexamen,phraseCertificat FROM ResultatExamen WHERE idformation=40 GROUP BY idApprenant,nom,prenom,idformateur, NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,datedebutexamen,phraseCertificat HAVING SUM(note) >= (SELECT SUM(note) / 2 AS noteExam FROM totalNoteExam );

create table page(
    idpage serial primary key,
    idFormateur int,
    logo varchar,
    couleurPrincipale varchar,
    couleurArrierePlan varchar,
    CouleurTitre varchar,
    couleurText varchar,
    couleurBouton varchar,
    couleurtextBouton varchar,
FOREIGN KEY (idFormateur) REFERENCES Formateur(idFormateur)
);

insert into page(idFormateur,logo,couleurPrincipale,couleurArrierePlan,CouleurTitre,couleurText,couleurBouton,couleurtextBouton) values(8,'sary.jpg','#a7d97c','#a7d97c','#a7d97c','#a7d97c','#a7d97c','#a7d97c');

UPDATE formateur SET 
    logo = 'sss',
    couleurPrincipale = 'vdss',
    couleurArrierePlan = 'zzzzzzzzz',
    CouleurTitre = 'dddddd',
    couleurText = 'iiiiii',
    couleurBouton = 'oooo',
    couleurtextBouton = 'aaa' 
WHERE 
    idformateur = 8;


SELECT page.*, Formateur.Nom, Formateur.Prenom, Formateur.NomOrgannisme FROM page JOIN Formateur ON page.idFormateur=Formateur.idFormateur; where page.idformateur=8;


SELECT * FROM formation  
                 JOIN categorie ON formation.idcategorie = categorie.idcategorie 
                 JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces 
                 JOIN langues ON formation.langues = langues.idLangues 
                 JOIN unite ON formation.unite = unite.idUnite
                 WHERE idFormateur =8  and etatPublication=1
                 ORDER BY formation.idformation DESC;


SELECT formation.idformation, formation.idformateur, formation.idcategorie, formation.typesacces, formation.langues, formation.titre, formation.duree, formation.unite, formation.resumer, formation.pdc, formation.token, formation.etat, formation.prix, formation.datedajout, formation.devalidation, formation.dedemande,  categorie.nom AS nomCategorie, typesacces.idTypesAcces, typesacces.nom AS nomTypesAcces, langues.idLangues, langues.nom AS nomLangues, unite.idUnite, unite.nom AS nomUnite,Formateur.nomespace
FROM formation
JOIN categorie ON formation.idcategorie = categorie.idcategorie
JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces
JOIN langues ON formation.langues = langues.idLangues
JOIN unite ON formation.unite = unite.idUnite
JOIN Formateur ON formation.idformateur = Formateur.idFormateur
WHERE nomespace='Ced.com'ORDER BY formation.idformation DESC;



SELECT * FROM formation
                 JOIN categorie ON formation.idcategorie = categorie.idcategorie 
                 JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces 
                 JOIN langues ON formation.langues = langues.idLangues 
                 JOIN unite ON formation.unite = unite.idUnite 
                 WHERE idFormateur =8 and etatPublication=0
                 ORDER BY formation.idformation DESC;


 String sql ="SELECT * FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite WHERE idFormateur =8 and etatPublication=1 ORDER BY formation.idformation DESC";

 SELECT formation.*,categorie.nom AS categorie_nom,typesacces.nom AS types_acces_nom,langues.nom AS langues_nom,unite.nom AS unite_nom FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite WHERE idformation = 20; ORDER BY formation.idformation DESC;

SELECT * FROM formation 
                JOIN categorie ON formation.idcategorie = categorie.idcategorie 
                 JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces 
                 JOIN langues ON formation.langues = langues.idLangues 
                 JOIN unite ON formation.unite = unite.idUnite 
                 WHERE idFormation = 20; 
                 ORDER BY formation.idformation DESC;

                 Select * from formation  join categorie on formation.idcategorie=categorie.idcategorie join typesacces on formation.typesacces=typesacces.idTypesAcces join langues on formation.langues=langues.idLangues join unite on formation.unite=unite.idUnite Where formation.etat=2

                 SELECT * FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite WHERE idFormation =20;


                 SELECT * FROM formation JOIN categorie ON formation.idcategorie = categorie.idcategorie JOIN typesacces ON formation.typesacces = typesacces.idTypesAcces JOIN langues ON formation.langues = langues.idLangues JOIN unite ON formation.unite = unite.idUnite JOIN inscritFormation ON formation.idFormation = inscritFormation.idFormation Join formateur on formation.idformateur=formateur.idformateur WHERE formation.etat = 2 AND etatpublication=2 and formateur.nomespace='Cedi'and  inscritFormation.idApprenant = 3 ORDER BY idinscritFormation DESC;


                 SELECT idApprenant, nom_apprenant, prenom_apprenant, messages,tokenApprenant, vue,type,date FROM messagePrive WHERE (idApprenant, date) IN (SELECT idApprenant, MAX(date) AS max_date FROM messagePrive WHERE idformateur = 10 GROUP BY idApprenant);
SELECT idApprenant, nom_apprenant, prenom_apprenant, messages,tokenApprenant, date 
FROM messagePrive 
WHERE (idApprenant, date) IN (
    SELECT idApprenant, MAX(date) AS max_date 
    FROM messagePrive 
    WHERE idformateur = 10 
    GROUP BY idApprenant
)
ORDER BY date desc;

SELECT COUNT(*) AS nombre_de_vues
FROM (
    SELECT idApprenant, nom_apprenant, prenom_apprenant, messages, tokenApprenant, vue,type, date
    FROM messagePrive
    WHERE (idApprenant, date) IN (
        SELECT idApprenant, MAX(date) AS max_date
        FROM messagePrive
        WHERE idformateur = 10
        GROUP BY idApprenant
    )
    AND vue = 0 and type=2
) AS sous_requete;


SELECT COUNT(*) AS nombre_de_vues
FROM (
    SELECT idformateur, nom_formateur, prenom_formateur, messages, token, vue,type, date
    FROM messagePrive
    WHERE (idformateur, date) IN (
        SELECT idformateur, MAX(date) AS max_date
        FROM messagePrive
        WHERE idapprenant = 10
        GROUP BY idformateur
    )
    AND vue = 0 and type=1
) AS sous_requete;

SELECT idApprenant, nom_apprenant, prenom_apprenant, messages,tokenApprenant,type,vue,date FROM messagePrive WHERE (idApprenant, date) IN (SELECT idApprenant, MAX(date) AS max_date FROM messagePrive WHERE idformateur =10 GROUP BY idApprenant)ORDER BY date desc;


SELECT SUM(note) AS noteApprenant,idApprenant,nom,prenom,idFormateur,NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,DateExamen,phraseCertificat FROM ResultatExamen WHERE idformation =27 GROUP BY idApprenant,nom,prenom,idformateur, NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,DateExamen,phraseCertificat HAVING SUM(note) >= (SELECT SUM(note) / 2 AS noteExam FROM totalNoteExam WHERE idExamen =61);

 
select sum(note) as note from reponsesexamen join questionexamen on reponsesexamen.idquestion=questionexamen.idquestion where idExamen=61;

***********Pourcentage admis et non admis************

WITH totalInscrits AS (
    SELECT COUNT(*) as total
    FROM inscritformation
    WHERE idformation = 39
),
totalAdmis AS (
    SELECT COUNT(*) as total
    FROM (
        SELECT idApprenant
        FROM resultatexamen
        WHERE idformation = 39
        GROUP BY idApprenant
        HAVING SUM(note) >= (SELECT SUM(note) / 2 FROM totalNoteExam WHERE idExamen =68)
    ) as admis
),
totalNonAdmis AS (
    SELECT COUNT(*) as total
    FROM (
        SELECT idApprenant
        FROM resultatexamen
        WHERE idformation = 27
        GROUP BY idApprenant
        HAVING SUM(note) <= (SELECT SUM(note) / 2 FROM totalNoteExam WHERE idExamen =68)
    ) as Nonadmis
)
SELECT
(SELECT COUNT(*) as totalInscrits FROM inscritformation WHERE idformation = 39),
(SELECT COUNT(*) as Admis FROM (SELECT idApprenant FROM resultatexamen WHERE idformation = 39 GROUP BY idApprenant HAVING SUM(note) >= (SELECT SUM(note) / 2 FROM totalNoteExam WHERE idExamen =68))as admis),
(SELECT COUNT(*) as NonAdmis FROM (SELECT idApprenant FROM resultatexamen WHERE idformation = 39 GROUP BY idApprenant HAVING SUM(note) <= (SELECT SUM(note) / 2 FROM totalNoteExam WHERE idExamen =68))as Nonadmis),
(totalAdmis.total::float / totalInscrits.total::float) * 100 AS pourcentageAdmis,
(totalNonAdmis.total::float / totalInscrits.total::float) * 100 AS pourcentageNonAdmis

FROM totalInscrits, totalAdmis,totalNonAdmis;


********Pourcentage non admis*********

WITH totalInscrits AS (
    SELECT COUNT(*) as total
    FROM inscritformation
    WHERE idformation = 27
),

totalNonAdmis AS (
    SELECT COUNT(*) as total
    FROM (
        SELECT idApprenant
        FROM resultatexamen
        WHERE idformation = 27
        GROUP BY idApprenant
        HAVING SUM(note) <= (SELECT SUM(note) / 2 FROM totalNoteExam WHERE idExamen =61)
    ) as admis
)
SELECT (totalNonAdmis.total::float / totalInscrits.total::float) * 100 AS pourcentageNonAdmis
FROM totalInscrits, totalNonAdmis;

*****************************



SELECT formation.*, 
       COALESCE(moyennes.moyenne_note, 0) AS moyenne_note
FROM formation
 
LEFT JOIN (
    SELECT idformation, AVG(note) AS moyenne_note
    FROM noteformation
    GROUP BY idformation
) moyennes ON formation.idformation = moyennes.idformation
WHERE idFormateur = 10
ORDER BY moyenne_note DESC;



WITH totalInscrits AS (
    SELECT idformation, COUNT(*) AS totalInscrits
    FROM inscritformation
    GROUP BY idformation
),
totalAdmis AS (
    SELECT re.idformation, COUNT(DISTINCT re.idApprenant) AS totalAdmis
    FROM resultatexamen re
    JOIN Examens ex ON re.idExamen = ex.idExamen
    GROUP BY re.idformation
    HAVING SUM(re.note) >= (
        SELECT SUM(note) / 2
        FROM resultatexamen
        WHERE idformation = 44
    )
)
SELECT 
    f.idformation, 
    f.titre,
    COALESCE((ta.totalAdmis::float / ti.totalInscrits::float) * 100, 0) AS tauxReussite
FROM formation f
LEFT JOIN totalInscrits ti ON f.idformation = ti.idformation
LEFT JOIN totalAdmis ta ON f.idformation = ta.idformation
where idformateur=10
ORDER BY tauxReussite DESC;

*************************

SELECT
    f.idformation,
    f.titre,
    COALESCE(SUM(ifo.droitpaye), 0) AS sommeDroitPaye,
    f.depenseformation,
    (COALESCE(SUM(ifo.droitpaye), 0)-f.depenseformation) as benefice,
    from(
        select sum(benefice) 
    )

FROM
    formation f
LEFT JOIN inscritFormation ifo ON f.idformation = ifo.idformation
where idformateur=10
GROUP BY f.idformation, f.titre
ORDER BY f.idformation;

**************************

SELECT
    SUM(sub.sommeDroitPaye) AS sommeTotaleDroitPaye
FROM
    (
        SELECT
            f.idformation,
            f.titre,
            COALESCE(SUM(ifo.droitpaye), 0) AS sommeDroitPaye
        FROM
            formation f
        LEFT JOIN inscritFormation ifo ON f.idformation = ifo.idformation
        WHERE f.idformateur = 10
        GROUP BY f.idformation, f.titre
    ) AS sub;


    

    SELECT idformateur,idApprenant,nom_formateur ,prenom_formateur, messages,token,vue,type,date FROM messagePrive WHERE (idFormateur, date) IN (SELECT idformateur, MAX(date) AS max_date FROM messagePrive WHERE idapprenant =10 GROUP BY idformateur)ORDER BY date desc;



    *************************

elearning=# select*from examens;
 idexamen | idformation |           titreexamen           | dateexamen | timer |   datedebutexamen   |    datefinexamen
----------+-------------+---------------------------------+------------+-------+---------------------+---------------------
       68 |          39 | QCM sur la Formation Leadership | 2024-06-05 |       | 2024-06-07 08:15:00 | 2024-06-07 08:45:00
(1 row)

elearning=# select*from reponsesexamen;

idreponse | note
-----------+------
       265 |    2
       171 |    1
       172 |    1
       173 |    1
       177 |    0
       180 |    3
       183 |    1
       184 |    1
       185 |    1
 
elearning=# select*from reponsesapprenant;
 idreponseapprenant | idexamen | idapprenant | idreponse
--------------------+----------+-------------+-----------
(0 rows)
************Marina jiaby**************
insert into reponsesapprenant(idexamen,idapprenant,idreponse) values (69,21,171),(69,21,172),(69,21,173),(69,21,176),(69,21,180),(69,21,183),(69,21,184),(69,21,185);
****************************************
insert into reponsesapprenant(idexamen,idapprenant,idreponse) values(68,23,149),(68,23,153),(68,23,155),(68,23,170),(68,23,162),(68,23,165),(68,23,166),(68,23,170);

**********admisListe***************





 

 /************************Admis indivduel**********************************************/
  SELECT SUM(note) AS noteApprenant,idApprenant,idExamen,idFormation,nom,prenom FROM ResultatExamen WHERE idExamen =69 and idapprenant=12  GROUP BY idApprenant,idExamen,idFormation,nom,prenom HAVING SUM(note) >= (SELECT SUM(note) / 2 AS noteExam FROM totalNoteExam WHERE idExamen =69);

******NONAdmis indivduel**********
SELECT SUM(note) AS noteApprenant,idApprenant,idExamen,idFormation FROM ResultatExamen WHERE idExamen =69 and idapprenant=14  GROUP BY idApprenant,idExamen,idFormation HAVING SUM(note) <= (SELECT SUM(note) / 2 AS noteExam FROM totalNoteExam WHERE idExamen =69);

 WITH 
totalInscrits AS (
    SELECT COUNT(*) as total
    FROM inscritformation
    WHERE idformation = 44
),
totalAdmis AS (
    SELECT COUNT(*) as total
    FROM (
        SELECT idApprenant
        FROM resultatexamen
        WHERE idformation = 44
        GROUP BY idApprenant
        HAVING SUM(note) >= (SELECT SUM(note) / 2 FROM totalNoteExam WHERE idExamen = 69)
    ) as admis
),
totalNonAdmis AS (
    SELECT (ti.total - ta.total) as total
    FROM totalInscrits ti, totalAdmis ta
)
SELECT
    ti.total as totalInscrits,
    ta.total as Admis,
    tn.total as NonAdmis,
    (ta.total::float / ti.total::float) * 100 AS pourcentageAdmis,
    (tn.total::float / ti.total::float) * 100 AS pourcentageNonAdmis
FROM
    totalInscrits ti, totalAdmis ta, totalNonAdmis tn;


WITH totalInscrits AS (
    SELECT idformation, COUNT(*) AS totalInscrits
    FROM    
    GROUP BY idformation
)
with totalAdmis AS (
    SELECT re.idformation, COUNT(DISTINCT re.idApprenant) AS totalAdmis
    FROM resultatexamen re
    JOIN Examens ex ON re.idExamen = ex.idExamen
    GROUP BY re.idformation
    HAVING SUM(re.note) >= (
        SELECT SUM(note) / 2
        FROM resultatexamen
        WHERE idformation = re.idformation 
    )
)
with totalNonAdmis AS (
    SELECT re.idformation, COUNT(DISTINCT re.idApprenant) AS nonAdmis
    FROM resultatexamen re
    JOIN Examens ex ON re.idExamen = ex.idExamen
    GROUP BY re.idformation
    HAVING SUM(re.note) <= (
        SELECT SUM(note) / 2
        FROM resultatexamen
        WHERE idformation = re.idformation
    )
)

SELECT 
    f.idformation, 
    f.titre,
    COALESCE((na.totalNonAdmis::float - ta.totalAdmis::float) * 100, 0) AS tauxReussite
FROM formation f
LEFT JOIN totalInscrits ti ON f.idformation = ti.idformation
LEFT JOIN totalNonAdmis na ON f.idformation = na.idformation
LEFT JOIN totalAdmis ta ON f.idformation = ta.idformation

where idformateur=10
ORDER BY tauxReussite DESC;

LEFT JOIN totalInscrits ti ON f.idformation = ti.idformation

SELECT SUM(note) AS noteApprenant,idApprenant,nom,prenom,idFormateur,
    NomFormateur,PrenomFormateur,nomorgannisme,idExamen,idFormation,TitreFormation,datedebutexamen,phraseCertificat FROM ResultatExamen WHERE idformation = 44 GROUP BY idApprenant, nom, prenom, idformateur, NomFormateur, PrenomFormateur, nomorgannisme, idExamen, idFormation, TitreFormation, datedebutexamen, phraseCertificat HAVING SUM(note) >= (SELECT SUM(note) / 2 FROM totalNoteExam WHERE idExamen = 69) ORDER BY noteApprenant DESC;

 
 
 
/**************note*****************/

create or replace view totalNoteExamFormation as
SELECT
    tne.idreponse,
    tne.reponse,
    tne.idquestion,
    tne.note,
    tne.idexamen,
    tne.idtypequestion,
    tne.question,
    e.idformation,
    e.titreexamen,
    e.datedebutexamen,
    e.datefinexamen
FROM
    totalNoteExam tne
JOIN
    examens e ON tne.idexamen = e.idexamen;



 

    8888888888888888888888*********yessssssss tena izy**********



    -- CTE pour la note nécessaire pour être admis
-- Calcul du total des inscrits par formation
-- Calcul du total des inscrits par formation
WITH totalInscrits AS (
    SELECT 
        i.idformation,
        COUNT(DISTINCT i.idApprenant) AS total
    FROM 
        inscritformation i
    GROUP BY 
        i.idformation
),
 
examSeuils AS (
    SELECT 
        tnf.idformation,
        tnf.idExamen,
        SUM(tnf.note) / 2 AS seuil
    FROM 
        totalNoteExamFormation tnf
    GROUP BY 
        tnf.idformation,
        tnf.idExamen
),
 
examResults AS (
    SELECT 
        re.idformation,
        re.idExamen,
        re.idApprenant,
        SUM(re.note) AS totalNote
    FROM 
        resultatexamen re
    GROUP BY 
        re.idformation,
        re.idExamen,
        re.idApprenant
),
 
totalAdmis AS (
    SELECT 
        re.idformation,
        re.idExamen,
        COUNT(DISTINCT re.idApprenant) AS total
    FROM 
        examResults re
    JOIN examSeuils es ON re.idformation = es.idformation AND re.idExamen = es.idExamen
    WHERE 
        re.totalNote >= es.seuil
    GROUP BY 
        re.idformation,
        re.idExamen
),
 
totalFailed AS (
    SELECT 
        re.idformation,
        re.idExamen,
        COUNT(DISTINCT re.idApprenant) AS total
    FROM 
        examResults re
    JOIN examSeuils es ON re.idformation = es.idformation AND re.idExamen = es.idExamen
    WHERE 
        re.totalNote < es.seuil
    GROUP BY 
        re.idformation,
        re.idExamen
),
 
totalNotPassed AS (
    SELECT 
        i.idformation,
        es.idExamen,
        COUNT(DISTINCT i.idApprenant) AS total
    FROM 
        inscritformation i
    LEFT JOIN examResults er ON i.idApprenant = er.idApprenant AND i.idformation = er.idformation
    JOIN examSeuils es ON i.idformation = es.idformation
    WHERE 
        er.idApprenant IS NULL
    GROUP BY 
        i.idformation,
        es.idExamen
)
 
SELECT
    f.idformation,
    f.titre,
    es.idExamen,
    ti.total AS totalInscrits,
    COALESCE(ta.total, 0) AS Admis,
    COALESCE(tf.total, 0) + COALESCE(tnp.total, 0) AS NonAdmis,
    ROUND((COALESCE(ta.total, 0)::numeric / NULLIF(ti.total, 0)::numeric) * 100, 2) AS tauxreussite,
    ROUND(((COALESCE(tf.total, 0) + COALESCE(tnp.total, 0))::numeric / NULLIF(ti.total, 0)::numeric) * 100, 2) AS pourcentageNonAdmis
FROM
    totalInscrits ti
JOIN
    examSeuils es ON ti.idformation = es.idformation
JOIN
    formation f ON f.idformation = ti.idformation
LEFT JOIN
    totalAdmis ta ON es.idExamen = ta.idExamen AND es.idformation = ta.idformation
LEFT JOIN
    totalFailed tf ON es.idExamen = tf.idExamen AND es.idformation = tf.idformation
LEFT JOIN
    totalNotPassed tnp ON es.idExamen = tnp.idExamen AND es.idformation = tnp.idformation
WHERE
    f.idformateur = 10
ORDER BY
    f.idformation, es.idExamen;

 