/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eletronicCashApp;

import javax.swing.JOptionPane;
import static eletronicCashApp.Main.nt10;
import static eletronicCashApp.Main.nt100;
import static eletronicCashApp.Main.nt2;
import static eletronicCashApp.Main.nt20;
import static eletronicCashApp.Main.nt5;
import static eletronicCashApp.Main.nt50;
import static eletronicCashApp.Main.vc;

/**
 *
 * @author cristiano
 */
public class Menus {

    public static void menuPrincipal() throws NumberFormatException {
        try {
            String opc;
            // ops == opção que o usuario ira escolher
            int op;
            // op == opção que o usuario ira escolher (Sera convertida de String para Inteiro)

            //Ele irá calcular quantos $ tem em caixa 
            //e Exibir no Menu Main
            vc = (nt100 * 100 + nt50 * 50 + nt20 * 20 + nt10 * 10 + nt5 * 5 + nt2 * 2);
            opc = JOptionPane.showInputDialog(null,
                    "$ em Caixa :" + vc
                    + "\n1 - Saque\n"
                    + "2 - Deposito\n"
                    + "0 - SAIR", "Menu Principal", JOptionPane.INFORMATION_MESSAGE);
            //Após pegarmos a opção que o usuario escolheu
            //Iremos verificar se ele clicou em "Cancelar" ou no "X"
            if (opc == null) {
                JOptionPane.showMessageDialog(null,
                        "Obrigado Por Usar nosso Caixa "
                        + "\n Até Mais Edneya :)", "Menu Principal", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            //Agora convertemos
            op = Integer.parseInt(opc);
            // e iremos verificar qual foi a opção escolhida e ir para o metódo correspondente
            if (op == 1) {
                menuSaque();
            }

            if (op == 2) {
                menuDeposito();
            }

            if (op == 0) {
                //Caso Escolha Sair
                JOptionPane.showMessageDialog(null,
                        "Obrigado Por Usar nosso Caixa\n"
                        + "Até Mais Edneya :)", "Menu Principal", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

            //Agora caso ele insira um Numero/Opção maior ou menor que as 3 Disponiveis
            if (op >= 3 || op < 0) {
                JOptionPane.showMessageDialog(null,
                        "Opção Invalida\n"
                        + "Favor Parar de Avacalhar :( ", "Menu Principal", JOptionPane.ERROR_MESSAGE);
                menuPrincipal();
            }

        } catch (NumberFormatException e) {
            //Tratamendo de erro, caso ele tenha inserido uma Letra ou Simbolo
            JOptionPane.showMessageDialog(null,
                    "Poxa Edneya :(\n"
                    + "Opção Invalida!\n"
                    + "Favor Parar de Avacalhar! :)", "Menu Principal", JOptionPane.ERROR_MESSAGE);
            menuPrincipal();
        }

    }

    //////////////////////////////////////////////////////////
    public static void menuSaque() throws NumberFormatException {
        try {

            // Calculando o Valor em Caixa 
            // Para Saber se será possivel Prover o Pedido
            //Faremos um IF onde caso não haja DINHEIRO no Caixa ele Logo ira avisar
            // Que Não Há Dinheiro e Pedira Para o Cliente Retornar Mais Tarde 
            //vc = Valor em Caixa (Valor Total do $ que o caixa tem no Momento)
            vc = (nt100 * 100 + nt50 * 50 + nt20 * 20 + nt10 * 10 + nt5 * 5 + nt2 * 2);
            if (vc == 0) {
                JOptionPane.showMessageDialog(null,
                        "Não há Cédulas em CAIXA\n"
                        + "Favor Aguardar Reabastecimento."
                        , "Menu Saque", JOptionPane.WARNING_MESSAGE);
                menuPrincipal();

            }
            String op;
            double pdd = 0;
            //pdd = Pedido (Pedido de Saque QUe Sera Feito Pelo Usuario)
            //Vamos Peagr o pedido do usuario 
            op = JOptionPane.showInputDialog(null,
                    "0 - Voltar\n"
                    + "Faça o Pedido De Saque Abaixo"
                    , "Menu Saque", JOptionPane.INFORMATION_MESSAGE).replaceAll(",",".");
            
            //Verificamos se ele clicou em "Cancelar" ou no "X"
            if (op == null) {
                JOptionPane.showMessageDialog(null,
                        "Obrigado Por Usar nosso Caixa "
                        + "\n Até Mais Edneya :)"
                        , "Menu Principal", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            
            
            pdd = Double.parseDouble(op);
            
            
            if (pdd == 0) {
                menuPrincipal();
            }
            // IF para caso ele peça um valor que o disponivel em caixa
            if (pdd > vc) {
                JOptionPane.showMessageDialog(null, "Não Temos Dinheiro Suficiente"
                        + "\n para Prover o Pedido de :R$" + pdd
                        + "\n Favor Inserir Valor Menor", "Menu Saque", JOptionPane.ERROR_MESSAGE);
                menuSaque();
            }

            //If para caso ele queira sacar $1 ou $3 (Impossivel Prover)
            if (pdd == 1 || pdd == 3) {
                JOptionPane.showMessageDialog(null, "Não Podemos Prover"
                        + "\n O Pedido de :R$" + pdd
                        + "\n Pois Não Há Combinação De Cédulas "
                        + "\n Que alcance tal Valor..."
                        + "\n Favor Inserir Outro Valor ", "Menu Saque", JOptionPane.WARNING_MESSAGE);
                menuSaque();
            }
            //IF para caso ele Insira um valor Negativo ou um numero como 0,100 afim
            //De Sacar R$100 :)
            if (pdd < 0 || (pdd > 0 && pdd < 1)) {
              if(pdd < 0){
                JOptionPane.showMessageDialog(null,
                        "Você Inseriu um Pedido"
                        + "\n De Valor Negativo !"
                        + "\n Favor Inserir Pedido Corretamente.",
                        "Menu Saque", JOptionPane.ERROR_MESSAGE);
                menuSaque();
            }else{
                JOptionPane.showMessageDialog(null,
                        + pdd + "É um Pedido"
                        + "\n De Valor Improprio !"
                        + "\n Favor Inserir Pedido Corretamente.",
                        "Menu Saque", JOptionPane.ERROR_MESSAGE);
                menuSaque();  
              }
              
              
              
            }     

            Main.vpdd = pdd;
            Process.provSaque(pdd);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Poxa Edneya :(\n"
                    + "Opção Invalida!\n"
                    + "Favor Parar de Avacalhar! :)", "Menu Saque", JOptionPane.ERROR_MESSAGE);
            menuSaque();
        }

    }

    //////////////////////////////////////////////////////////// 
    public static void menuDeposito() throws NumberFormatException {
        try {
            // Metodo Para Reabastecer/Depositar no CAIXA
            int op = 0;
            String opc;

            //op == Opção
            //Vamos Ver Quais Cédulas o Usuario Quer inserir
            opc = JOptionPane.showInputDialog(null,
                    "Escoha uma Cédula para reabastecer : \n"
                    + "1 - Notas de R$100\n"
                    + "2 - Notas de R$50\n"
                    + "3 - Notas de R$20\n"
                    + "4 - Notas de R$10\n"
                    + "5 - Notas de R$5\n"
                    + "6 - Notas de R$2\n"
                    + "0 - Voltar", "Menu Deposito", JOptionPane.QUESTION_MESSAGE);

            if (opc == null) {
                JOptionPane.showMessageDialog(null,
                        "Obrigado Por Usar nosso Caixa "
                        + "\n Até Mais Edneya :)", "Menu Principal", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

            op = Integer.parseInt(opc);

            //Caso Seja 0(ZERO) ira Voltar ao Menu Main como Mostrado na imagem
            if (op == 0) {
                menuPrincipal();
            }
            //If para caso ele insira um Valor/Opção maior que 6 ou Negativo
            if (op > 6 || op < 0) {
                JOptionPane.showMessageDialog(null,
                        "Poxa Edneya :(\n"
                        + "Opção Invalida!\n"
                        + "Favor Parar de Avacalhar! :)", "Menu Deposito", JOptionPane.ERROR_MESSAGE);
                menuDeposito();
            }
            //Agora chamamos o Metodo que reabastece ,passandoa  nota escolhida 
            Process.reab(op);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Poxa Edneya :(\n"
                    + "Opção Invalida!\n"
                    + "Favor Parar de Avacalhar! :)", "Menu Deposito", JOptionPane.ERROR_MESSAGE);
            menuDeposito();
        }

    }
    ///////////////////////////////////////////////////////////////////////////

}
