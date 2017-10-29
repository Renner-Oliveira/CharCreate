# CharCreate
Editar string de conexão em META-INF/persistence.xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/chameleon" /> - String de conexão aqui
<property name="javax.persistence.schema-generation.database.action" value="none" /> - Mudar para create na primeira vez que executar o projeto depois voltar para none
