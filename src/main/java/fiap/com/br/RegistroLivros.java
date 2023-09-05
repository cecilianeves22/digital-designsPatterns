package fiap.com.br;

import java.util.ArrayList;
import java.util.List;

// interface do Observer Pattern
interface Observer {
    void atualizar(RegistroLivros livro);
}

// Interface para as estratégias de notificação
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
}

// Implementação concreta da estratégia de notificação por SMS
class NotificacaoSMS implements NotificacaoStrategy {
    @Override
    public void notificar(RegistroLivros livro) {
        System.out.println("Notificação por SMS: O estoque do livro foi atualizado.");
        livro.mostrarInformacoes();
    }
}

public class RegistroLivros {
    private int quantidadeDisponivel;
    private String titulo;
    private String autor;
    private String isbn;
    private List<Observer> observadores = new ArrayList<>();
    private NotificacaoStrategy notificacaoStrategy; // Campo para a estratégia de notificação

    public RegistroLivros(String titulo, String autor, String isbn, int quantidadeDisponivel, NotificacaoStrategy notificacaoStrategy) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.notificacaoStrategy = notificacaoStrategy;
    }

    // getters e setters (com métodos adicionar e remover)

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public void adicionarObserve(Observer observador) {
        observadores.add(observador);
    }

    public void removerObserve(Observer observador) {
        observadores.remove(observador);
    }

    // adicionar livros ao estoque
    public void adicionarLivros(int quantidade) {
        quantidadeDisponivel += quantidade;
        notificarObservadores(); // Notificar depois da alteração no estoque
    }

    // para remover livros do estoque
    public void removerLivros(int quantidade) {
        if (quantidade <= quantidadeDisponivel) {
            quantidadeDisponivel -= quantidade;
            notificarObservadores();
        } else {
            System.out.println("Quantidade insuficiente de livros disponíveis.");
        }
    }

    // Método para exibir informações sobre o livro
    public void mostrarInformacoes() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Quantidade Disponível: " + quantidadeDisponivel);
    }

    // Método para definir a estratégia de notificação em tempo de execução
    public void setNotificacaoStrategy(NotificacaoStrategy notificacaoStrategy) {
        this.notificacaoStrategy = notificacaoStrategy;
    }

    // Método para notificar os observadores usando a estratégia atual
    private void notificarObservadores() {
        for (Observer observador : observadores) {
            observador.atualizar(this);
            // Usar a estratégia de notificação atual
            notificacaoStrategy.notificar(this);
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso no RegistroLivro com a estratégia de notificação por email
        RegistroLivros livro1 = new RegistroLivros("É assim que acaba", "Colleen Hoover", "978-85-01-11251-4", 10, new NotificacaoEmail());

        // Exibir informações sobre o livro
        livro1.mostrarInformacoes();

        // Adicionar um observador para acompanhar as alterações no estoque
        livro1.adicionarObserve(new Observer() {
            @Override
            public void atualizar(RegistroLivros livro) {
            }
        });

        // Adicionar livros ao estoque e notificar com a estratégia de notificação por email
        livro1.adicionarLivros(5);

        // Trocar a estratégia de notificação para SMS
        livro1.setNotificacaoStrategy(new NotificacaoSMS());

        // Remover livros do estoque e notificar com a nova estratégia de notificação por SMS
        livro1.removerLivros(3);

        // Exemplo de uso no RegistroLivro com a estratégia de notificação por SMS
        RegistroLivros livro2 = new RegistroLivros("Harry Potter e a Câmara Secreta", "J.K. Rowling", "978-06-06-38496-4", 5, new NotificacaoSMS());

        // Exibir informações sobre o livro
        livro2.mostrarInformacoes();

        // Adicionar um observador para acompanhar as alterações no estoque
        livro2.adicionarObserve(new Observer() {
            @Override
            public void atualizar(RegistroLivros livro) {
            }
        });

        // Adicionar livros ao estoque e notificar com a estratégia de notificação por SMS
        livro2.adicionarLivros(10);

        // Trocar a estratégia de notificação para Email
        livro2.setNotificacaoStrategy(new NotificacaoEmail());

        // Remover livros do estoque e notificar com a nova estratégia de notificação por Email
        livro2.removerLivros(5);
    }

}
