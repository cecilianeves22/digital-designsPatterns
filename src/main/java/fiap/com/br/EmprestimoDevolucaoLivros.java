package fiap.com.br;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDevolucaoLivros {
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

        public String getTitulo() {
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        public boolean estaEmprestado() {
            return emprestado;
        }

        @Override
        public void emprestar() {
            if (!emprestado) {
                emprestado = true;
                System.out.println("Livro emprestado com sucesso.");
            } else {
                System.out.println("Este livro já está emprestado.");
            }
        }

        @Override
        public void devolver() {
            if (emprestado) {
                emprestado = false;
                System.out.println("Livro devolvido com sucesso.");
            } else {
                System.out.println("Este livro não está emprestado no momento.");
            }
        }
    }

    public static class Aluno {
        private String nome;
        private List<Component> livrosEmprestados;

        public Aluno(String nome) {
            this.nome = nome;
            this.livrosEmprestados = new ArrayList<>();
        }

        public String getNome() {
            return nome;
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

        public void devolverLivro(Component livro) {
            if (livro instanceof Livro) {
                Livro livroIndividual = (Livro) livro;
                if (livrosEmprestados.contains(livroIndividual)) {
                    livrosEmprestados.remove(livroIndividual);
                    livroIndividual.devolver();
                } else {
                    System.out.println("Você não possui este livro emprestado.");
                }
            }
        }

        public void verificarDevolucaoPendente() {
            if (!livrosEmprestados.isEmpty()) {
                System.out.println("Lembrete: Você possui livros emprestados. Por favor, devolva-os.");
            }
        }
    }

    public static void main(String[] args) {
        EmprestimoDevolucaoLivros sistema = EmprestimoDevolucaoLivros.getInstancia();

        // Criando alguns livros
        Livro livro1 = sistema.new Livro("A biblioteca Meia Noite", "Matt Haig");
        Livro livro2 = sistema.new Livro("A rainha vermelha", "Victoria Aveyard");

        // Criando alguns alunos
        Aluno aluno1 = new Aluno("Sophie");
        Aluno aluno2 = new Aluno("Fabiane");

        // Empréstimos de livros
        aluno1.emprestarLivro(livro1);
        aluno2.emprestarLivro(livro2);

        // Tentativa de empréstimo do mesmo livro a outro aluno
        aluno2.emprestarLivro(livro1);

        // Devolução de livros
        aluno1.devolverLivro(livro1);
        aluno2.devolverLivro(livro2);

        // Tentativa de devolução de um livro não emprestado
        aluno2.devolverLivro(livro2);

        // Verificar devolução pendente
        aluno1.verificarDevolucaoPendente();
        aluno2.verificarDevolucaoPendente();
    }
}
