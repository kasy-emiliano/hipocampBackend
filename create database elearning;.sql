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

insert into Apprenant(idApprenant,nom,Prenom,email,mdp,Profession,civilite,modeDexercice,numero,datenaissance,token,etatCompte,dateDajout)values(1,'Cedric','Bolt','cedric@gmail.com','cedric',8,1,1,'0323212321','02-02-2002','abc',1,'16-01-2024');





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
    foreign key (civilite) references civilite(idcivilite),
    foreign key (Profession) references Profession(idProfession),
    foreign key (modeDexercice) references modeDexercice(idmodeDexercice)
);

insert into Formateur(nom,Prenom,email,mdp,NomOrgannisme,ville,civilite,Profession,modeDexercice,bio,numero,datenaissance,facebook,linkedin,token,etatCompte,Pdp,dateDajout)values('Kasy','Emiliano','kasy@gmail.com','kasy','Kaiamba','Tamatave',2,10,1,'Professeur Kasy','0343212321','2024-01-16','Kasy Jr','KasyJr','def',1,'sary.jpg','2024-01-16');

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
duree integer,
montantParJours double precision,
titre varchar,
resumer varchar,
lien varchar
);

    INSERT INTO publicite (NomOrganisme, sary, email, contact, dateDebut, dateFin, duree, montantParJours,titre,resumer,lien)
    VALUES 
    ('Google', 'mm.PNG', 'contact@google.com', '+1357924680', '2024-03-04 12:00:00', '2024-03-06 12:00:00', 7, 55.00,'sélo ou duo','une maniere de voir les choses entre deux ou tout seul','https://www.star.mg/historique');

        ('Samsung', 'image2.jpg', 'contact@samsung.com', '+1987654321', '2024-02-15 12:00:00', '2024-02-17 12:00:00', 7, 45.00,'music ou rien','music fort,une sentiment de comment aimer la music','https://www.star.mg/historique');
    ('Apple', 'image1.jpg', 'contact@apple.com', '+1234567890', '2024-02-14 12:00:00', '2024-02-17 12:00:00', 7, 50.00,'la viéé des etudiants','les etudiants sont comme les gens normal blablabla ','https://invivox.com/fr'),



    INSERT INTO publicite (NomOrganisme, sary, email, contact, dateDebut, dateFin, duree, montantParJours,titre,resumer,lien)
    VALUES 
    ('Google', 'uploads/mm.PNG', 'contact@google.com', '+1357924680', '2024-03-04 12:00:00', '2024-03-06 12:00:00', 7, 55.00,'sélo ou duo','une maniere de voir les choses entre deux ou tout seul','https://www.star.mg/historique');


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
idcategorie,
typesacces,langues,titre,duree,unite,resumer,pdc,token,etat,prix,datedajout,devalidation,dedemande
 from formation join categorie on formation.idcategorie=categorie.idcategorie join typesacces on formation.typesacces=typesacces.idTypesAcces join langues on formation.langues=langues.idLangues join unite on formation.unite=unite.idUnite;

 idformation | idformateur | idcategorie | typesacces | langues |                                                 titre                                                  | duree | unite |                                                    resumer                                                    |                     pdc                      |                    token                    | etat | prix | datedajout | devalidation | dedemande
 