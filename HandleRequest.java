import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;




// Lambda Handler para processar requisições
public class CadastroUsuarioHandler implements RequestHandler<Map<String, Object>, String> {

    // Cliente DynamoDB para interagir com o banco de dados
    private final AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
    // Gson para manipular JSON
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    // Nome da tabela no DynamoDB
    private static final String TABLE_NAME = "Usuarios";

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        try {
            // Converter entrada JSON para objeto Usuario
            Usuario usuario = gson.fromJson(gson.toJson(input), Usuario.class);

            // Validar campos obrigatórios
            if (usuario.id == null || usuario.nome == null || usuario.email == null) {
                return "Erro: Todos os campos (id, nome, email) são obrigatórios.";
            }

            // Criar item para inserção no DynamoDB
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("id", new AttributeValue(usuario.id));
            item.put("nome", new AttributeValue(usuario.nome));
            item.put("email", new AttributeValue(usuario.email));

            // Realizar operação de inserção (PutItem) no DynamoDB
            PutItemRequest request = new PutItemRequest().withTableName(TABLE_NAME).withItem(item);
            dynamoDB.putItem(request);

            // Retornar mensagem de sucesso
            return "Usuário cadastrado com sucesso!";
        } catch (Exception e) {
            // Registrar erro e retornar mensagem apropriada
            context.getLogger().log("Erro ao cadastrar usuário: " + e.getMessage());
            return "Erro ao cadastrar usuário: " + e.getMessage();
        }
    }
}
