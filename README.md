# CharCreate
Editar string de conexão em META-INF/persistence.xml
<code>property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/chameleon"</code> - String de conexão aqui
<code>property name="javax.persistence.schema-generation.database.action" value="none"</code> - Mudar para create na primeira vez que executar o projeto depois voltar para none
