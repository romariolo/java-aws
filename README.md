# README - Cadastro de Usuário com AWS Lambda em Java 🚀

## 💡 Descrição
Função AWS Lambda para cadastrar usuários em uma tabela DynamoDB usando Java.

## 🛠️ Requisitos
✅ AWS CLI configurado  
✅ JDK 11+  
✅ Maven  
✅ Permissões para Lambda e DynamoDB

## 🗂️ Estrutura do Projeto
📂 `src/main/java/com/example/CadastroUsuarioHandler.java`

## 🚀 Como Executar
1️⃣ **Compilar:** `mvn clean package`  
2️⃣ **Criar Lambda:** Via AWS Console/CLI  
3️⃣ **Deploy:** Subir `.jar` para a Lambda

## ⚠️ Observações
✔️ Crie uma tabela DynamoDB `Usuarios` com chave `id`.  
✔️ Configure permissões IAM para `dynamodb:PutItem`.

---
💙 Desenvolvido por Romário
