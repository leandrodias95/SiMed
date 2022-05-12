<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Funcionario</h1>
    <p class="mb-4">Formulário de Cadastro</p>

    <a class="btn btn-secondary mb-4" href="${pageContext.request.contextPath}/FuncionarioListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>
    <div class="row">
        <!-- Imagem do Documento --> 
        <div class="col">
            <div class="card shadow mb-4"> 
                <div class="card-body">
                    <div class="form-group">
                        <center>
                            <img alt="imagem" class="img-thumbnail" 
                                 src="${funcionario.imagemFuncionario}" 
                                 name="target" id="target" width="170" heigth="200">
                            <br></br>
                            <input type="file" id="gallery-photo-add" 
                                   class="inputfile" onchange="uploadFile();"/>
                            <label for="gallery-photo-add" class="btn btn-success">
                                <i class="fas fa-file-upload"></i> 
                                Selecionar Documento...
                            </label>
                        </center>
                    </div>
                </div>
            </div>
        </div>
        <!-- Campos de cadastramento -->        
        <div class="col-lg-9">
            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="form-group">
                        <label>ID:</label>
                        <input class="form-control" type="text" name="idFuncionario" id="idFuncionario" 
                               value="${funcionario.idfuncionario}" readonly="readonly"/>
                    </div>
                    <div class="form-group">
                        <label>Nome Funcionario :</label>
                        <input class="form-control" type="text" name="nomeFuncionario" id="nomeFuncionario" 
                               value="${funcionario.nomeFuncionario}" size="100" maxlength="100"/>
                    </div>
                    <div class="form-group">
                        <label>CPF :</label>
                        <input class="form-control" name="cpf" type="text" id="cpf" maxlength="14" onKeyDown="Mascara(this, Cpf);" onKeyPress="Mascara(this, Cpf);" onKeyUp="Mascara(this, Cpf);" 
                               value="${funcionario.cpf}" size="100" maxlength="100"/>
                    </div>
                    <div class="form-group">
                        <label>Turno:</label>
                        <input class="form-control" type="text" name="turno" id="turno" 
                               value="${funcionario.turno}" size="100" maxlength="100"/>
                    </div>
                    <div class="form-group">
                      <label>Cargo:</label>  
                    <select name="cargo" id="cargo">
                        <option value="">Selecione</option>
                        <c:forEach var="cargo" items="${cargos}">
                            <option value="${cargo.idcargo}"
                                    ${funcionario.cargo.idcargo == cargo.idcargo ? "selected":""}>
                                    ${cargo.nomecargo}
                            </option>
                        </c:forEach>
                    </select>
                
                    </div>
                    <!-- Botão de Confirmação --> 
                    <div class="form-group">
                        <button class="btn btn-success" type="submit" id="submit" onclick="validarCampos()">
                            Salvar Documento</button>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</div>

<style type="text/css">
    .inputfile {
        /* visibility: hidden etc. wont work */
        width: 0.1px;
        height: 0.1px;
        opacity: 0;
        overflow: hidden;
        position: absolute;
        z-index: -1;    }
    .inputfile:focus + label {
        /* keyboard navigation */
        outline: 1px dotted #000;
        outline: -webkit-focus-ring-color auto 5px;    }
    .inputfile + label * {
        pointer-events: none;    }
    .borda{
        position: relative;
        margin: 0 20px 30px 0;
        padding: 10px;
        border: 1px solid #e1e1e1;
        border-radius: 3px;
        background: #fff;
        -webkit-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        -moz-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        box-shadow: 0px 0px 3px rgba(0,0,0,0.06);    }
</style>

<script>
    
    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("nomeFuncionario").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o nome !',
                showConfirmButton: false,
                timer: 1000
            });
            $("#nomeFuncionario").focus();
        } else if (document.getElementById("cpf").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o cpf!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#cpf").focus();
        } else if (document.getElementById("turno").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o valor da despesa!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#turno").focus();
        } else {
            gravarDados();
        }
    }
    
    function gravarDados() {
        console.log("Gravando dados ....");
        var target = document.getElementById("target").src;
        $.ajax({
            type: 'post',
            url: 'FuncionarioCadastrar',
            data: {
                idFuncionario: $('#idFuncionario').val(),
                nomeFuncionario: $('#nomeFuncionario').val(),
                cpf: $('#cpf').val(),
                turno: $('#turno').val(),
                cargo: $('#cargo').val(),
                imagemFuncionario: target
            },
            success:
                    function (data) {
                        console.log("reposta servlet->");
                        console.log(data);
                        if (data == 1) {
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: 'Sucesso',
                                text: 'Funcionario gravado com sucesso!',
                                showConfirmButton: false,
                                timer: 1000
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar o Funcionario!',
                                showConfirmButton: false,
                                timer: 1000
                            })
                        }
                        window.location.href = "${pageContext.request.contextPath}/FuncionarioListar";
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/FuncionarioListar";
                    }
        });
    }
    
    function uploadFile() {
        //pega o componente html image 
        var target = document.getElementById("target");
        //limpa o image
        target.src = "";
        //abre a janela para seleção do arquivo.
        var file = document.querySelector("input[type='file']").files[0];
        //verifica se o arquivo existe
        if (file) {
            //faz a leitura do arquivo da imagem
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = function () {
                //atribui a imagem do arquivo ao componente html image
                target.src = reader.result;
            };
        } else {
            target.src = "";
        }
    }
     function uploadFile() {
        //pega o componente html image 
        var target = document.getElementById("target");
        //limpa o image
        target.src = "";
        //abre a janela para seleção do arquivo.
        var file = document.querySelector("input[type='file']").files[0];
        //verifica se o arquivo existe
        if (file) {
            //faz a leitura do arquivo da imagem
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = function () {
                //atribui a imagem do arquivo ao componente html image
                target.src = reader.result;
            };
        } else {
            target.src = "";
        }
    }
     function Mascara(campo, func) {
                obj = campo;
                fun = func;
                setTimeout("execmascara()", 1);
            }

            /Função que Executa os objetos e atribui valores a eles/
            function execmascara() {
                obj.value = fun(obj.value);
            }
            /Função que padroniza CPF/

            function Cpf(v) {
                //xxx.xxx.xxx-xx
                v = v.replace(/\D/g, "");
                v = v.replace(/(\d{3})(\d)/, "$1.$2");
                v = v.replace(/(\d{3})(\d)/, "$1.$2");
                v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
                return v

            }
</script>   
<jsp:include page="/footer.jsp"/>