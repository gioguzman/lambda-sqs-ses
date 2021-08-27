resource "aws_ses_template" "EmailTemplate" {
  name    = "EmailTemplate"
  subject = "Pronto llegar&aacute; tu pedido"
  html    = "<h1>Hola {{name_user}}</h1>tu articulo est&aacute; por llegar.<br><br>Detalle:<br><br><strong>Nombre: </strong> {{name_art}}<br><br><strong>Tama&ntilde;o: </strong>{{size}}<br><br><strong>Color: </strong>{{color}}"
}