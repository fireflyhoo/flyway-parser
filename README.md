# flyway-parser
Multi-statement sql file parsing ,  Migrate from flywaydb


用于多sql文件解析出 多条sql语句 , 是从 flywaydb migration  抠出的代码


```java
 public static void main(String[] args) {
        String sql = "select 1; insert into xxx values(1,1,1,1,1); select * from user ";

        final DatabaseType databaseType = new SQLiteDatabaseType();
        Configuration configuration = new Configuration();
        SqlScriptFactory sqlScriptFactory = databaseType.createSqlScriptFactory(configuration, new ParsingContext());
        SqlScript sqlScript = sqlScriptFactory.createSqlScript(new StringResource(sql),true,NoopResourceProvider.INSTANCE);
        SqlStatementIterator stm = sqlScript.getSqlStatements();
        stm.forEachRemaining((v) -> {
            System.out.println(v.getSql());
            System.out.println(v.canExecuteInTransaction());
        });

```
