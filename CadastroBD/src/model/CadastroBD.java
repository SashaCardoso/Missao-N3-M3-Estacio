package model;

import java.io.IOException;
import java.util.Scanner;

public class CadastroBD {

    public static void main(String[] args) {
        PessoaFisicaRepo pfRepo = new PessoaFisicaRepo();
        PessoaJuridicaRepo pjRepo = new PessoaJuridicaRepo();
        
        Scanner scan = new Scanner(System.in);
        String input;
        
        do {
            System.out.println("1. Add");
            System.out.println("2. Edit");
            System.out.println("3. Delete");
            System.out.println("4. Search by ID");
            System.out.println("5. Show all");
            System.out.println("6. Save data");
            System.out.println("7. Load data");
            System.out.println("0. Exit");

            input = scan.next();

            switch (input) {

                case "1":
                    do {
                        System.out.println("\n");
                        System.out.println("F - Pessoa Fisica");
                        System.out.println("J - Pessoa Juridica");
                        System.out.println("M - Menu");

                        input = scan.next();
                        scan.nextLine();

                        switch (input.toUpperCase()) {
          
                            case "F":
                                System.out.print("Digite o id da pessoa: ");
                                int idInformado = scan.nextInt();
                                System.out.println("Insira os dados... ");
                                scan.nextLine();
                                System.out.print("Nome: ");
                                String nome = scan.nextLine();
                                System.out.print("CPF: ");
                                String cpf = scan.nextLine();
                                System.out.print("Idade: ");
                                int idade = scan.nextInt();
                                
                                PessoaFisica pessoaFisica = new PessoaFisica(idInformado, nome, cpf, idade);
                                pfRepo.inserir(pessoaFisica);

                                System.out.println("Inclusao realizada com sucesso!");
                                pessoaFisica.exibir();
                                break;

                            case "J":
                                System.out.print("Digite o id da pessoa: ");
                                int idInformado2 = scan.nextInt();
                                scan.nextLine();
                                System.out.print("Nome: ");
                                nome = scan.nextLine();
                                System.out.print("CNPJ: ");
                                String cnpj = scan.nextLine();
       
                                PessoaJuridica pessoaJuridica = new PessoaJuridica(idInformado2, nome, cnpj);
                                pjRepo.inserir(pessoaJuridica);

                                System.out.println("Inclusao realizada com sucesso!");
                                pessoaJuridica.exibir();
                                break;
                                
                            case "M":                        
                                break;
                                
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }
                    } while (!input.equalsIgnoreCase("M"));
                    break;

                case "2":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | M - Menu");

                        input = scan.next();
                        scan.nextLine();

                        switch (input.toUpperCase()) {

                            case "F":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaFisica = scan.nextInt();
                                scan.nextLine();
                                
                                PessoaFisica pessoaFisicaLocalizada = null;
                                try {
                                    pessoaFisicaLocalizada = pfRepo.obter(idPessoaFisica);
                                } catch (Exception e) { }

                                if (pessoaFisicaLocalizada != null) {
                                    pessoaFisicaLocalizada.exibir();

                                    System.out.println("Nome atual: " + pessoaFisicaLocalizada.getNome());
                                    System.out.print("Novo nome: ");
                                    String novoNome = scan.nextLine();

                                    System.out.println("CPF atual: " + pessoaFisicaLocalizada.getCpf());
                                    System.out.print("Novo CPF: ");
                                    String novoCPF = scan.nextLine();

                                    System.out.println("Idade atual: " + pessoaFisicaLocalizada.getCpf());
                                    System.out.print("Nova Idade: ");
                                    int novaIdade = scan.nextInt();
                                    
                                    pessoaFisicaLocalizada.setNome(novoNome);
                                    pessoaFisicaLocalizada.setCpf(novoCPF);
                                    pessoaFisicaLocalizada.setIdade(novaIdade);

                                    pfRepo.alterar(pessoaFisicaLocalizada);

                                    System.out.println("Pessoa alterada com sucesso!");
                                } else
                                    System.out.println("Pessoa nao localizada! ");
                                break;

                            case "J":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaJuridica = scan.nextInt();
                                scan.nextLine();

                                PessoaJuridica pessoaJuridicaLocalizada = null;
                                try {
                                    pessoaJuridicaLocalizada = pjRepo.obter(idPessoaJuridica);
                                } catch (Exception e) { }

                                if (pessoaJuridicaLocalizada != null) {
                                    pessoaJuridicaLocalizada.exibir();

                                    System.out.println("Nome atual: " + pessoaJuridicaLocalizada.getNome());
                                    System.out.println("Novo nome: ");
                                    String novoNome = scan.nextLine();

                                    System.out.println("CNPJ atual: " + pessoaJuridicaLocalizada.getCnpj());
                                    System.out.println("Novo CNPJ: ");
                                    String novoCNPJ = scan.nextLine();
                                    
                                    pessoaJuridicaLocalizada.setNome(novoNome);
                                    pessoaJuridicaLocalizada.setCnpj(novoCNPJ);

                                    pjRepo.alterar(pessoaJuridicaLocalizada);

                                    System.out.println("Pessoa alterada com sucesso!");
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;
                                
                            case "M":                        
                                break;
                                
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }
                    } while (!input.equalsIgnoreCase("M"));
                    break;

                case "3":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | M - Menu");

                        input = scan.next();
                        scan.nextLine();

                        switch (input.toUpperCase()) {

                            case "F":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaFisica = scan.nextInt();

                                PessoaFisica pessoaFisicaLocalizada = null;
                                try {
                                    pessoaFisicaLocalizada = pfRepo.obter(idPessoaFisica);
                                } catch (Exception e) { }

                                if (pessoaFisicaLocalizada != null) {
                                    pessoaFisicaLocalizada.exibir();
                                    pfRepo.excluir(idPessoaFisica);

                                    System.out.println("Pessoa excluida com sucesso!");
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;


                            case "J":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaJuridica = scan.nextInt();

                                PessoaJuridica pessoaJuridicaLocalizada = null;
                                try {
                                    pessoaJuridicaLocalizada = pjRepo.obter(idPessoaJuridica);
                                } catch (Exception e) { }

                                if (pessoaJuridicaLocalizada != null) {
                                    pessoaJuridicaLocalizada.exibir();

                                    pjRepo.excluir(idPessoaJuridica);

                                    System.out.println("Pessoa excluida com sucesso!");
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;
                            
                            case "M":                        
                                break;

                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }

                    } while (!input.equalsIgnoreCase("M"));
                    break;

                case "4":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | M - Menu");

                        input = scan.next();
                        scan.nextLine();

                        switch (input.toUpperCase()) {

                            case "F":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaFisica = scan.nextInt();

                                PessoaFisica pessoaFisicaLocalizada = null;
                                try {
                                    pessoaFisicaLocalizada = pfRepo.obter(idPessoaFisica);
                                } catch (Exception e) { }

                                if (pessoaFisicaLocalizada != null) {
                                    System.out.println("Pessoa localizada!");
                                    pessoaFisicaLocalizada.exibir();
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;

                            case "J":
                                System.out.println("Digite o ID da pessoa: ");
                                int idPessoaJuridica = scan.nextInt();

                                PessoaJuridica pessoaJuridicaLocalizada = null;
                                try {
                                    pessoaJuridicaLocalizada = pjRepo.obter(idPessoaJuridica);
                                } catch (Exception e) { }

                                if (pessoaJuridicaLocalizada != null) {
                                    System.out.println("Pessoa localizada!");

                                    pessoaJuridicaLocalizada.exibir();
                                } else
                                    System.out.println("Pessoa nao localizada!");
                                break;

                            case "M":                        
                                break;    
                                
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }

                    } while (!input.equalsIgnoreCase("M"));
                    break;

                case "5":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | M - Menu");

                        input = scan.next();
                        scan.nextLine();

                        switch (input.toUpperCase()) {

                            case "F":
                                System.out.println("Lista de pessoas Fisicas: ");
                                pfRepo.obterTodos()
                                        .forEach(pessoaFisica -> {
                                            pessoaFisica.exibir();
                                            System.out.println();
                                        });
                                break;

                            case "J":
                                System.out.println("Lista de pessoas juridicas: ");
                                pjRepo.obterTodos()
                                        .forEach(pessoaJuridica -> {
                                            pessoaJuridica.exibir();
                                            System.out.println();
                                        });
                                break;
                                
                            case "M":                        
                                break;    

                            default:
                                System.out.println("Opcao invalida");
                                break;
                        }

                    } while (!input.equalsIgnoreCase("M"));
                    break;

                case "6":
                    System.out.println("Escolha o nome do arquivo");
                    input = scan.next();
                    scan.nextLine();
                    try {
                        pfRepo.persistir(input+".fisica.bin");
                        pjRepo.persistir(input+".juridica.bin");
                    } catch (IOException error) {
                        System.out.println("Erro ao persistir/salvar os dados: " + error.getMessage());
                    }
                    break;

                case "7":
                    System.out.println("Informe o nome do arquivo salvo");
                    input = scan.next();
                    scan.nextLine();
                    try {
                        pfRepo.recuperar(input+".fisica.bin");
                        pjRepo.recuperar(input+".juridica.bin");
                    } catch (ClassNotFoundException | IOException errorr) {
                        System.out.println("Erro ao recuperar os dados: " + errorr.getMessage());
                    }
                    break;
                
                case "0":
                    System.out.println("Sistema Finalizado com sucesso.");
                    break;
                   
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while (!input.equals("0"));   
        scan.close();
    }
}
