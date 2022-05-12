<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    
    <h1 class="h3 mb-2 text-gray-800">Material Hospitalar</h1>
    <p class="mb-4">Planilha de Registro</p>
    
   <a class="btn btn-danger mb-4" href="${pageContext.request.contextPath}/MaterialNovo">
        
        <strong>Novo</strong>
    </a>
        <div class="card shadow">
            <div class="card-body">
                <table id="datatable" class="display">
    <thead>
        <tr>
            <th align="right">ID</th>
            <th align="left">Nome</th>
            <th align="center">Quantidade</th>
            <th align="right">Categoria</th>
            <th align="center">Excluir</th>
            <th align="center">Alterar</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="material" items="${materiais}">
            <tr>
        <td align="right">${material.idMaterialHospitalar}</td>
        <td align="left">${material.nomeMaterial}</td>
        <td align="left">${material.qtde}</td>
        <td align="left">${material.categoria.nomeCategoria}</td>

        
        <td align="center">
        <a href="#" id="deletar" title="Excluir" onclick="deletar(${material.idMaterialHospitalar})">
            <button class="btn btn-danger">Excluir</button>
        </a></td>
        
        <td align="center">
            <a href="${pageContext.request.contextPath}/MaterialCarregar?idMaterialHospitalar=${material.idMaterialHospitalar}">
                <button class="btn btn-warning">Alterar</button>
            </a>
        </td>
    </tr>
        </c:forEach>
    </tbody>
</table>
            </div>
        </div>
</div>

    <script>
    $(document).ready(function() {
            console.log('entrei ready');
            //Carregamos a datatable
            //$("#datatable").DataTable({});
            $('#datatable').DataTable({
                "oLanguage": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Nenhum registro encontrado.",
                    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Seguinte",
                        "sLast": "Último"
                    }
                }
            });
        });
    
    function deletar(codigo){
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            text: "Você não poderá recuperar depois!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, apague o Material!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/MaterialExcluir',
                    data:{
                        idMaterialHospitalar: id
                    },
                    success:
                        function(data){
                            if(data == 1){
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'success',
                                    title: 'Sucesso',
                                    text: 'Material excluída com sucesso!',
                                    showConfirmButton: false,
                                    timer: 2000
                                })
                            } else {
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: 'Não foi possível excluir o Material!',
                                    showConfirmButton: false,
                                    timer: 2000
                                })
                            }
                            window.location.href = "${pageContext.request.contextPath}/MaterialListar";
                        },
                    error:
                        function(data){
                            window.location.href = "${pageContext.request.contextPath}/MaterialListar";
                        }
                });
            };
        });
    }
</script>
 <%@include file="/footer.jsp"%>