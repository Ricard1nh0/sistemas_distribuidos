## Passos para Execução

### 1. Organize os Arquivos

Crie uma pasta em seu computador (por exemplo: `C:\ProjetoSensores`) e salve os dois arquivos `.java` dentro dela.

### 2. Abra o Prompt de Comando (cmd)

### 3. Navegue até a Pasta do Projeto

Use o comando `cd` para entrar na pasta que você criou.

### 4. Compile os Arquivos Java

javac ServidorUDP.java SensorClienteUDP.java

### 5. Inicie o Servidor

java ServidorUDP

### 6. Inicie os Clientes

Abra um novo terminal para cada sensor que desejar simular 

Navegue até a pasta do projeto

java SensorClienteUDP Sensor-Temperatura

java SensorClienteUDP Sensor-Umidade

java SensorClienteUDP Sensor-Pressao