package com.example.elearning.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class inscritFormation {

 private int idinscritFormation;
 private int  idApprenant;
 private int  idFormation;
 private String dateDajout;
 public int getIdinscritFormation() {
  return idinscritFormation;
 }

 public void setIdinscritFormation(int idinscritFormation) {
  this.idinscritFormation = idinscritFormation;
 }

 public int getIdApprenant() {
  return idApprenant;
 }

 public void setIdApprenant(int idApprenant) {
  this.idApprenant = idApprenant;
 }

 public int getIdFormation() {
  return idFormation;
 }

 public void setIdFormation(int idFormation) {
  this.idFormation = idFormation;
 }

 public String getDateDajout() {
  return dateDajout;
 }

 public void setDateDajout(String dateDajout) {
  this.dateDajout = dateDajout;
 }

 public inscritFormation(){}


 public inscritFormation(int idApprenant, int idFormation, String dateDajout) {
  this.idApprenant = idApprenant;
  this.idFormation = idFormation;
  this.dateDajout = dateDajout;
 }

 public inscritFormation(int idinscritFormation, int idApprenant, int idFormation, String dateDajout) {
  this.idinscritFormation = idinscritFormation;
  this.idApprenant = idApprenant;
  this.idFormation = idFormation;
  this.dateDajout = dateDajout;
 }

 public void insererWithid(int idApprenant, int idFormation) throws Exception {
  LocalDate date = LocalDate.now();

  // Formater la date en "année-mois-jour"
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  String formattedDate = date.format(formatter);
  String sql="Insert into inscritFormation(idApprenant,idFormation,dateDajout) values ("+idApprenant+","+idFormation+",'"+formattedDate+"')";
  FonctionBase.execute(sql);


 }

 public void insererWithemail(String emaiApprenant,int idFormation) throws Exception {

Apprenant A=FonctionBase.findByEmail(emaiApprenant);
  LocalDate date = LocalDate.now();

  // Formater la date en "année-mois-jour"
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  String formattedDate = date.format(formatter);
if(A!=null){
 String sql="Insert into inscritFormation(idApprenant,idFormation,dateDajout) values ("+A.getIdApprenant()+","+idFormation+",'"+formattedDate+"')";
 FonctionBase.execute(sql);

}

 }

 public void supprimer(int idApprenant,int idFormation) throws Exception {

  String sql="delete inscritFormation where idApprenant ="+idApprenant+" AND idFormation="+idFormation;

  FonctionBase.execute(sql);


 }





}
