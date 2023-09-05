# digital-designsPatterns
Exercício 5 - FIAP - Digital Business

Os designs patterns utilizados nesse projeto foram: 

- Observer
- Singleton
- Strategy 
- Component 

O *observer* define o método atualizar() na classe RegistroLivros, e então a classe RegistroLivros será o "assunto" observável que notificará seus observadores quando o estado mudar. Começando por aqui:
```java
interface Observer {
    void atualizar(RegistroLivros livro);
}
```
O *singleton* a classe EmprestimoDevolucaoLivros utiliza o padrão Singleton para garantir que apenas uma única instância do sistema de gerenciamento de tarefas seja criada. O restante do código permanece inalterado. Começando por aqui:

```java
// Classe Singleton
    private static EmprestimoDevolucaoLivros instancia = null;

    // Construtor privado para evitar a criação de instâncias externas
    private EmprestimoDevolucaoLivros() {
    }

    // Método estático para obter a instância única do Singleton
    public static EmprestimoDevolucaoLivros getInstancia() {
        if (instancia == null) {
            instancia = new EmprestimoDevolucaoLivros();
        }
        return instancia;
    }
```

O *strategy* define uma interface EstrategiaNotificacao que representa diferentes estratégias de notificação. Em seguida, implementa duas classes concretas (NotificacaoSMS e NotificacaoEmail) para definir as estratégias de notificação, na classe RegistroLivros. Assim, permitindo que altere as estratégias de notificação sem modificar o código da classe RegistroLivros.
Partes que esta usando:
```java
interface NotificacaoStrategy {
    void notificar(RegistroLivros livro);
}

// Implementação concreta da estratégia de notificação por email
class NotificacaoEmail implements NotificacaoStrategy {
    @Override
    public void notificar(RegistroLivros livro) {
        System.out.println("Notificação por Email: O estoque do livro foi atualizado.");
        livro.mostrarInformacoes();
        
    }

    // Implementação concreta da estratégia de notificação por SMS
class NotificacaoSMS implements NotificacaoStrategy {
    @Override
    public void notificar(RegistroLivros livro) {
        System.out.println("Notificação por SMS: O estoque do livro foi atualizado.");
        livro.mostrarInformacoes();
    }
}
}
```

O *component* é usado para criar uma estrutura de árvore onde um aluno pode interagir com diferentes tipos de componentes (nesse caso, livros) de maneira uniforme, tratando todos os componentes da mesma forma, independentemente de serem livros individuais ou outros tipos de componentes. Partes que está sendo usado: 
```java
public interface Component {
        void emprestar();
        void devolver();
    }

    public class Livro implements Component {
        private String titulo;
        private String autor;
        private boolean emprestado;

        public Livro(String titulo, String autor) {
            this.titulo = titulo;
            this.autor = autor;
            this.emprestado = false; // Inicialmente, o livro não está emprestado
        }

        public List<Component> getLivrosEmprestados() {
            return livrosEmprestados;
        }

        public void emprestarLivro(Component livro) {
            if (livro instanceof Livro) {
                Livro livroIndividual = (Livro) livro;
                if (!livroIndividual.estaEmprestado()) {
                    livrosEmprestados.add(livroIndividual);
                    livroIndividual.emprestar();
                } else {
                    System.out.println("O livro já está emprestado a outro aluno.");
                }
            }
        }
```
