<%-- 
    Document   : upload
    Created on : 04/08/2018, 08:30:01 PM
    Author     : César
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <form id="upload-form" class="upload-box" action="upload?accion=upload" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label >Descripcion</label>
                            <input type="text" name="descripcion" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="file" id="file" name="file" />
                            <span id="upload-error" class="error"></span>
                        </div>
                        <input type="submit"  class="btn btn-success" id="upload-button" value="upload" />
                    </form>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label >IdDescarga</label>
                        <input type="text" name="uploadId" class="form-control">
                    </div>
                    <a href="upload?accion=download" class="btn btn-success">Donwload</a>
                </div>
            </div>
            <div class="row">
                <div class="col col-md-4">
                    <label for="">Selecciona</label><br>
                    <div id="data">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-success" id="insertar">GUARDAR</button>
                        <button class="btn btn-success" id="cargar" onclick="obtener(29)">CARGAR</button>
                    </div>
                </div>

                <div class="col col-md-4">
                    <form action="checkbox?accion=agregar" method="post">
                        <div class="checkbox-group"><input type="checkbox" name="hobbies" value="Dancing">Dancing</div>
                        <div class="checkbox-group"> <input type="checkbox" name="hobbies" value="Reading">Reading</div>
                        <div class="checkbox-group"><input type="checkbox" name="hobbies" value="Singing">Singing</div>
                        <div class="checkbox-group"><input type="checkbox" name="hobbies" value="Programming">Programming</div>
                        <div class="checkbox-group"><input type="checkbox" name="hobbies" value="Sleeping">Sleeping</div>
                        <input type="submit" value="GUARDAR" class="btn btn-success">
                    </form>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <label>Selecciona Equipo</label>
                    <select class="form-control" id="cboEquipo" name="cboEquipo">

                    </select>
                </div>
                <div class="col-md-6">
                    <label>Selecciona Hincha</label>
                    <select class="form-control" id="cboHincha" name="cboHincha">
                    </select>
                </div>
            </div>
        </div>
        <script src="jQuery/jQuery-2.1.3.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="js/datajs.js"></script>

    </body>
</html>
