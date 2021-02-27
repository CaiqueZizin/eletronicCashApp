/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eletronicCashApp;

import javax.swing.JOptionPane;
import static eletronicCashApp.Main.backgdnt5;
import static eletronicCashApp.Main.nt10;
import static eletronicCashApp.Main.nt100;
import static eletronicCashApp.Main.nt2;
import static eletronicCashApp.Main.nt20;
import static eletronicCashApp.Main.nt5;
import static eletronicCashApp.Main.nt50;
import static eletronicCashApp.Main.valorntGuardada;
import static eletronicCashApp.Main.vnts10;
import static eletronicCashApp.Main.vnts100;
import static eletronicCashApp.Main.vnts2;
import static eletronicCashApp.Main.vnts20;
import static eletronicCashApp.Main.vnts5;
import static eletronicCashApp.Main.vnts50;
import static eletronicCashApp.Main.vpdd;

/**
 *
 * @author cristiano
 */
public class Process {

    public static void provSaque(double pdd) {
        //Se o VI  for Maior que 110 iremos encaminhalo a um metodo que 
        // ira o decrementar e o devolver abaixo de 110
        if (pdd > 110) {
            pdd = CalAte_110(pdd);
            //Caso após a Decrementação o VI seja 110,Podemos Finalizalo em NotasPar

        }

        if (pdd % 2 == 0) {

            if ((pdd - 1) % 5 == 0 || (pdd - 3) % 5 == 0) {
                // Veremos se o Pedido termina em 6 ou 8, porque caso termine podemos ainda utilizar o notasPar
                // Isso Foi Feito Para caso o pedido seja algo parecido com 26 
                // O NotasSeis é Necessario Porque há um Macete Para chegar a 6 e 8
                //  Ir Aumentando de $2 em $2
                notasSeis(pdd);

            } else {
                // o  ElSE é para corrigir, quando se colocava 28 ele executava o notasSeis e o NotasPar em seguida :)
                // caso o pedido para saque seja Par ele executara perfeitamente com notasPAr
                notasPar(pdd);
            }

        }

        if (pdd % 2 != 0) {
            //Agora caso o pedido seja  impar...
            //Guardaremos uma Nota de $5 Pois a mesma é Crucial para formar 
            //As Combinações  Impares,Isso para que ele não gaste todas as Notas
            //de 5 
            //

            if (pdd % 5 == 0 || (pdd - 2) % 5 == 0) {
                // Veremos se o Pedido termina em 5 ou 7, porque caso termine podemos ainda utilizar o notasPar
                // Isso Foi Feito Para caso o pedido seja algo parecido com 105
                // antes ele Daria 1x50 2x20 1x5 e 5x2 usando o NotasImpar
                // Porem estaria errado pois o jeito mais rapido ou seja o CERTO é 1x100e 1x5

                //Para Numeros Terminados em 7 há o Macete de dar $5 + $2
                //Então Da Para usar o notasPar
                notasPar(pdd);

            }

            if ((pdd + 1) % 5 == 0) {
                // Veremos se o Pedido termina em 9   
                // Isso Foi Feito Para caso o pedido seja algo parecido com 9 
                // antes ele daria notas demais usando o NotasImpar
                // basta dar 1x5 e 2x2

                notasNove(pdd);

            } else {
                notasImpar(pdd);
            }

        }

    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
    public static void notasPar(double vi) {

        //Como este é o notasPar seu Código é mais simples
        //Ele Apenas ira decrementar o VI(Valor Inserido/$ Pedido)
        //Até O Mesmo Atingir Zero (0)
        //vi == Valor Pedido
        //Vamos Solucionar o Erro onde
        // Caso ele tenha (3 Notas de 100) e (5 de 50)
        // e eu peço 240 
        // ele diz que não pode prover esse valor
        //MOstra Suas Notas,porém Me da o valor até onde ele pode chegar
        // Exemplo ele me da $200
        //Isso é um BACKUP(Um Rascunho do estoque REAL) das Notas de 100
        //Onde caso ele veja que não consegue me dar o valor todo
        // ele não ira ter mechido no Estoque  de Verdade que são -> (nt100,nt50,etc)
        int back100, back50, back20, back10, back5, back2;

        back100 = nt100 - vnts100;
        back50 = nt50 - vnts50;
        back20 = nt20 - vnts20;
        back10 = nt10 - vnts10;

        if (backgdnt5 > 0) {
            backgdnt5 = 0;
            valorntGuardada = 0;
            back5 = nt5 - vnts5 + backgdnt5;
        } else {
            back5 = nt5 - vnts5;
        }
        back2 = nt2 - vnts2;

        //Os Whiles seguem a logica enquanto o Caixa Tiver as Notas
        // eo VI for maior que o valor dessa Nota ele  ira decrementando
        while (back100 > 0 && vi >= 100) {

            vi = (vi - 100);
            back100--;
            vnts100++;

        }

        while (back50 > 0 && vi >= 50) {

            vi = (vi - 50);
            back50--;
            vnts50++;

        }

        while (back20 > 0 && vi >= 20) {

            vi = (vi - 20);
            back20--;
            vnts20++;

        }

        while (back10 > 0 && vi >= 10) {

            vi = (vi - 10);
            back10--;
            vnts10++;

        }

        while (back5 > 0 && vi >= 5) {

            vi = (vi - 5);
            back5--;
            vnts5++;

        }

        if (backgdnt5 > 0) {

            while ((back2 > 0 || vi - valorntGuardada == 0) && vi > 0) {

                if (vi - valorntGuardada == 0) {
                    vi = vi - 5;
                    backgdnt5 = 0;
                    vnts5++;
                    valorntGuardada = 0;
                }

                if (vi - 2 >= 0 && back2 > 0) {

                    vi = (vi - 2);
                    back2--;

                    vnts2++;
                }

            }
        } else {

            while (back2 > 0 && vi >= 2) {

                vi = (vi - 2);
                back2--;
                vnts2++;

            }
        }

        if (vi == 0) {

            //Caso ele veja que consegue prover %100  do pedido
            //Agora ele ira retirar as notas do estoue Para dar ao Usuario
            nt100 = nt100 - vnts100;
            nt50 = nt50 - vnts50;
            nt20 = nt20 - vnts20;
            nt10 = nt10 - vnts10;
            nt5 = nt5 - vnts5;

            nt2 = nt2 - vnts2;
            backgdnt5 = 0;
            JOptionPane.showMessageDialog(null,
                    "O Saque de : R$" + vpdd
                    + "\n Foi Fornecido Da Seguinte Forma :"
                    + "\n Notas R$100 : " + vnts100
                    + "\n Notas R$50 : " + vnts50
                    + "\n Notas R$20 : " + vnts20
                    + "\n Notas R$10 : " + vnts10
                    + "\n Notas R$5 : " + vnts5
                    + "\n Notas R$2 : " + vnts2
                    + "\n Agradecemos a Preferencia :)",
                    "Menu Saque", JOptionPane.INFORMATION_MESSAGE);
            //Agora ele ira zerar as variaveis que guardas as notas que 
            //ele deu ao Usuario, PAra que ele esteja Pronto para prover outro Saque
            // E esses Valores se AMONTOEM
            valorntGuardada = 0;
            vnts100 = 0;
            vnts50 = 0;
            vnts20 = 0;
            vnts10 = 0;
            vnts5 = 0;
            vnts2 = 0;
            //Agora ele Tendo Provido,Voltara ao Menu de Saque,Pronto Pra +1 :)   
            Menus.menuSaque();
        } else {
            //ELSE para caso ele não possa prover %100 do Pedido
            //
            JOptionPane.showMessageDialog(null,
                    "Não Podemos Prover o Saque de :R$" + vpdd
                    + "\n Pois Possuimos as Seguintes Cédulas :"
                    + "\n Notas R$100 : " + nt100
                    + "\n Notas R$50 : " + nt50
                    + "\n Notas R$20 : " + nt20
                    + "\n Notas R$10 : " + nt10
                    + "\n Notas R$5 : " + nt5
                    + "\n Notas R$2 : " + nt2
                    + "\n Agradecemos a Preferencia :)",
                    "Menu Saque", JOptionPane.INFORMATION_MESSAGE);
            //Novamente ele zera as variaveis contendo a QUANTIDADE e os Valores
            //Que Seriam Providos ao Usuario
            valorntGuardada = 0;

            backgdnt5 = 0;
            vnts100 = 0;
            vnts50 = 0;
            vnts20 = 0;
            vnts10 = 0;
            vnts5 = 0;
            vnts2 = 0;
            //Após o Usuario Dar OK ele Retorna ao Menu de Saque
            Menus.menuSaque();

        }

    }

    public static void notasImpar(double vi) {
        //No notasImpar iremos decrementar nosso vi caso ele seja maior que 110
        //Para que fique mais facil trabalhar com ele
        //Após isso iremos identificar em que posição ele se encontra
        //Daremos um valor a nossa Variavel VALOR
        //que sera passada junto ao vi a um metodo que ira finalizalos

        // vi == ValorInserido que é IGUAL a PDD == PEDIDO	
        //Valor == Variavel que ira Auxiliar para conclusão do cálculo
        //Que ira prover o saque correto
        int valor = 0;

        // Ja decrementado agora iremos descobrir em que faixa esse numero impar se encontra
        if (vi > 100) {
            // entre 100 e 110
            // Estando entre 100 e 110 nosso valor recebe 95
            valor = 95;
            //Passaremos o valor e o vi para o metodo que ira finalizar
            calcAteValor(valor, vi);
        }

        if (vi > 90) {
            //entre 90 e 100
            //Nosso valor recebe 85 
            valor = 85;
            //Passaremos o valor e o vi para o metodo que ira finalizar
            calcAteValor(valor, vi);

        }

        if (vi > 80) {
            // entre 80 e 90
            //Nosso valor recebe 75
            valor = 75;
            calcAteValor(valor, vi);

        }

        if (vi > 70) {
            valor = 65;
            calcAteValor(valor, vi);

        }

        if (vi > 60) {

            valor = 55;
            calcAteValor(valor, vi);

        }

        if (vi > 50) {

            valor = 45;
            calcAteValor(valor, vi);

        }

        if (vi > 40) {

            valor = 35;
            calcAteValor(valor, vi);

        }

        if (vi > 30) {

            valor = 25;
            calcAteValor(valor, vi);

        }

        if (vi > 20) {

            valor = 15;
            calcAteValor(valor, vi);

        }

        if (vi > 10) {

            valor = 5;
            calcAteValor(valor, vi);

        }

        if (vi > 1) {

            valor = 5;
            calcAteValor(valor, vi);

        }

    }

    public static void notasSeis(double vi) {
        //a Logica do notasSeis é parecida com a do notasImpar
        //Decrementar até VI estiver abaixo de 110
        //Econtrar a Posição onde este numero 6 ou 8 esta
        // !! e o Diferencial será o valor que a variavel VALOR irá receber

        //Valor == Variavel que ira Auxiliar para conclusão do cálculo
        //Que ira prover o saque correto
        int valor = 0;

        // Agora vamos Procurar esse 6 ou esse 8  *-*
        if (vi > 100) {
            // entre 100 e 110

            valor = 100;
            calcAteValor(valor, vi);
        }

        if (vi > 90) {
            //entre 90 e 100

            valor = 90;
            calcAteValor(valor, vi);
        }

        if (vi > 80) {
            // entre 80 e 90

            valor = 80;
            calcAteValor(valor, vi);
        }

        if (vi > 70) {

            valor = 70;
            calcAteValor(valor, vi);
        }

        if (vi > 60) {

            valor = 60;
            calcAteValor(valor, vi);
        }

        if (vi > 50) {

            valor = 50;
            calcAteValor(valor, vi);
        }

        if (vi > 40) {

            valor = 40;
            calcAteValor(valor, vi);
        }

        if (vi > 30) {

            valor = 30;
            calcAteValor(valor, vi);
        }

        if (vi > 20) {

            valor = 20;
            calcAteValor(valor, vi);
        }

        if (vi > 10) {

            valor = 10;
            calcAteValor(valor, vi);
        }

        if (vi > 1) {
            valor = 0;
            calcAteValor(valor, vi);
        }

    }

    public static void notasNove(double vi) {
        // Logica também parecida com o notasSeis e notasImpar
        //Novamente irá Decrementar até VI estiver abaixo de 110
        //Econtrar a Posição onde este numero 6 ou 8 esta
        // !! e o Diferencial será o valor que a variavel VALOR irá receber

        int valor = 0;

        // Agora vamos Procurar esse 9  *-*
        if (vi > 100) {
            // entre 100 e 110

            valor = 105;
            calcAteValor(valor, vi);
        }

        if (vi > 90) {
            //entre 90 e 100

            valor = 95;
            calcAteValor(valor, vi);
        }

        if (vi > 80) {
            // entre 80 e 90

            valor = 85;
            calcAteValor(valor, vi);
        }

        if (vi > 70) {

            valor = 75;
            calcAteValor(valor, vi);
        }

        if (vi > 60) {

            valor = 65;
            calcAteValor(valor, vi);
        }

        if (vi > 50) {

            valor = 55;
            calcAteValor(valor, vi);
        }

        if (vi > 40) {

            valor = 45;
            calcAteValor(valor, vi);
        }

        if (vi > 30) {

            valor = 35;
            calcAteValor(valor, vi);
        }

        if (vi > 20) {

            valor = 25;
            calcAteValor(valor, vi);
        }

        if (vi > 10) {

            valor = 15;
            calcAteValor(valor, vi);
        }

        if (vi > 1) {
            valor = 5;

            calcAteValor(valor, vi);
        }

    }

    public static double CalAte_110(double vi) {
        // Esse metodo ira decrementar o VI(Valor Inserido/$ Solicitado Pelo Usuario)
        //ate um numero abaixo de 110
        //onde sera retornado para ser finalizado

        //Mais uma vez usaremos as Notas de Mentirinha/RascunhoDoEstoque hehehe
        //Apenas para seguir calculando e ver se conseguiremos
        int back100 = 0;
        int back50 = 0;
        int back20 = 0;
        int back10 = 0;
        int back5 = 0;
        int back2 = 0;

        back100 = nt100;
        back50 = nt50;
        back20 = nt20;
        back10 = nt10;
        if (nt5 > 1) {
            backgdnt5 = 1;
            valorntGuardada = 5;
            back5 = nt5 - 1;
        } else {
            back5 = nt5;
        }

        back2 = nt2;

        //Abaixo os Whiles vão decrementar o vi enquanto tiverem tais cédulas
        //e o vi seja maior que o valor da cédula
        //e ja ira almentando as VNTS(Valor Notas/Notas que Serão Dadas ao Usuario)
        while (back100 > 0 && vi > 110) {

            vi = (vi - 100);
            back100--;
            vnts100++;

        }

        while (back50 > 0 && vi > 110) {

            vi = (vi - 50);
            back50--;
            vnts50++;

        }

        while (back20 > 0 && vi > 110) {

            vi = (vi - 20);
            back20--;
            vnts20++;

        }

        while (back10 > 0 && vi > 110) {

            vi = (vi - 10);
            back10--;
            vnts10++;

        }

        while (back5 > 0 && vi > 110) {

            vi = (vi - 5);
            back5--;
            vnts5++;

        }

        while (back2 > 0 && vi > 110) {

            vi = (vi - 2);
            back2--;
            vnts2++;

        }
        //Com o vi ja abaixo de 110
        //ele ira retornar o vi decrementado

        return vi;

    }
    /////////////////////////////////////

    public static void calcAteValor(int valor, double vi) {
        //Este Metodo é o Metodo que ira  finalizar notasImpar  notasSeis e  notasNOve

        // num == Numero que nós usaremos para tentar chegar ao valor 
        //Da VARIAVEL VALOR
        //Parav que saibamos se será possivel prover o saque
        int num = 0;
        int valor2 = valor - 1;
        //Novamente as Notas de Mentirinha/Rascunho do Estoque
        int back100 = 0;
        int back50 = 0;
        int back20 = 0;
        int back10 = 0;
        int back5 = 0;
        int back2 = 0;

        //BAckup Para nota de 5 (Que Ser guardada Por segurança)
        back100 = nt100 - vnts100;
        back50 = nt50 - vnts50;
        back20 = nt20 - vnts20;
        back10 = nt10 - vnts10;

        if (backgdnt5 == 0 && nt5 > 1) {
            backgdnt5 = 1;
            valorntGuardada = 5;
            back5 = nt5 - vnts5 - backgdnt5;
        } else {
            back5 = nt5 - vnts5 - backgdnt5;
        }
        back2 = nt2 - vnts2;

        //Os Whiles iram incrementar o num até o mesmo ser igual a variavel VALOR
        //ver se há tal nota em estoque
        //ver se num(Que Inicia como 0) + Valor Da Nota seja MENOR ou IGUAL
        while (back100 > 0 && num + 100 <= valor) {
            num = num + 100;
            back100--;
            vnts100++;
        }

        while (back50 > 0 && num + 50 <= valor) {
            num = num + 50;
            back50--;
            vnts50++;
        }

        while (back20 > 0 && num + 20 <= valor) {
            num = num + 20;
            back20--;
            vnts20++;
        }

        while (back10 > 0 && num + 10 <= valor) {
            num = num + 10;
            back10--;
            vnts10++;
        }
        while (back5 > 0 && num + 5 <= valor) {
            num = num + 5;
            back5--;
            vnts5++;

        }
        while (back2 > 0 && num + 2 <= valor) {

            num = num + 2;
            back2--;
            vnts2++;

        }

        // Antes se tivessemos EXEMPLO 10x100 e pedissemos
        // $9 ele ficava em loop pois havia um while{} errado e não havia o IF abaixo
        // IF serve para verificar se o conseguimos colocar o NUM no mesmo valor que o VALOR
        if (num == valor || num == valor2) {

            vi = vi - num;

            //Caso consiga ele ira aumentando esse num(que  agora é igual ao VALOR) de $2 em $2 
            //Até alcançar o valor de VI
            //VALOR que como ja mencionamos, tera um valor diferente em cada metódo
            // e em cada posição onde o VI se encontrar após decrementação
            if (backgdnt5 > 0) {

                while ((back2 > 0 || vi - valorntGuardada == 0) && vi > 0) {

                    if (vi - 5 == 0 && backgdnt5 > 0) {

                        vi = vi - 5;
                        backgdnt5 = 0;
                        vnts5++;
                        valorntGuardada = 0;
                    }

                    if (vi - 2 >= 0 && back2 > 0) {

                        vi = (vi - 2);
                        back2--;

                        vnts2++;
                    }

                }
            } else {

                while (back2 > 0 && vi >= 2) {

                    vi = (vi - 2);
                    back2--;
                    vnts2++;

                }

            }

            mostraSaldo(num, vi);
        } else {
            //Se ele não conseguir colocar num no mesmo vallor que o VALOR
            //Ele ja ira chamar o metodo que mostra resultado final
            //onde será mostrado que com as cédulas atuais em caixa não é Possivel
            //Prover o saque
            mostraSaldo(num, vi);
        }
        //Agora tendo conseguido ele ira passar o num e o vi 
        //que agora possuem o mesmo valor lara o metódo que ira confirmar
        //que foi possivel prover o pedido do usuario e ira mostrar 
        //com quais Notas(vnts) foi provido o saque

    }

    public static void mostraSaldo(int num, double vi) {
        // Agora veremos se num == vi
       
        if (vi == 0) {
        //Se SIM então quer dizer que podemos prover o PEDIDO

            //Agora sim Decrementammos nosso Estoque de Verdade
            //Diminuindo ele com as Notas que serão fornecidas (VNTS)
            nt100 = nt100 - vnts100;
            nt50 = nt50 - vnts50;
            nt20 = nt20 - vnts20;
            nt10 = nt10 - vnts10;
            nt5 = nt5 - vnts5;
            nt2 = nt2 - vnts2;
            backgdnt5 = 0;
            JOptionPane.showMessageDialog(null,
                    "O Saque de R$" + vpdd
                    + "\n Foi Fornecido Da Seguinte Forma :"
                    + "\n Notas R$100 : " + vnts100
                    + "\n Notas R$50 : " + vnts50
                    + "\n Notas R$20 : " + vnts20
                    + "\n Notas R$10 : " + vnts10
                    + "\n Notas R$5 : " + vnts5
                    + "\n Notas R$2 : " + vnts2
                    + "\n Agradecemos a Preferencia :)",
                    "Menu Saque", JOptionPane.INFORMATION_MESSAGE);

            //Zeramos as Notas o valor das Notas Fornecidas
            //PAra  que ele esteja pronto Pra +1 :)
            // e Zerando o Valor da Nota Guardada($5) se é que guardamos
            valorntGuardada = 0;
            vnts100 = 0;
            vnts50 = 0;
            vnts20 = 0;
            vnts10 = 0;
            vnts5 = 0;
            vnts2 = 0;
            Menus.menuSaque();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Não Podemos Prover o Saque de :R$" + vpdd
                    + "\nPois Possuimos as Seguintes Cédulas :"
                    + "\n Notas R$100 : " + nt100
                    + "\n Notas R$50 : " + nt50
                    + "\n Notas R$20 : " + nt20
                    + "\n Notas R$10 : " + nt10
                    + "\n Notas R$5 : " + nt5
                    + "\n Notas R$2 : " + nt2
                    + "\n Favor Tentar Outro Valor",
                    "Menu Saque", JOptionPane.INFORMATION_MESSAGE);
            //Zeramos as Notas o valor das Notas Fornecidas
            //Para  que ele esteja pronto Pra +1 :) 
            valorntGuardada = 0;
            backgdnt5 = 0;
            vnts100 = 0;
            vnts50 = 0;
            vnts20 = 0;
            vnts10 = 0;
            vnts5 = 0;
            vnts2 = 0;
            Menus.menuSaque();
        }

    }

    public static void reab(int nota) throws NumberFormatException {
        // Aqui vamos reabastecer a nota que o usuario esccolheu
        //nota == Nota escolhida pelo usuario
        String opc;
        int nts, nt = 0;
        // nt sera usada para mostrar o valor da nota escolhida no menu de reabastecimento
        try {
            //nts == Notas que serão inseridas
            // nt == nota
            if (nota == 1) {
                nt = 100;
            }
            if (nota == 2) {
                nt = 50;
            }
            if (nota == 3) {
                nt = 20;
            }
            if (nota == 4) {
                nt = 10;
            }
            if (nota == 5) {
                nt = 5;
            }
            if (nota == 6) {
                nt = 2;
            }

            
            opc = JOptionPane.showInputDialog(null,
                    "Informe a Quantidade de Notas"
                    + "\n  de : R$" + nt
                    + "\n a serem inseridas "
                    + "\n 0 - Voltar", "Menu Deposito", JOptionPane.QUESTION_MESSAGE);

            if (opc == null) {
                JOptionPane.showMessageDialog(null,
                        "Obrigado Por Usar nosso Caixa "
                        + "\n Até Mais Edneya :)", "Menu Principal", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            nts = Integer.parseInt(opc);

            if (nts == 0) {
                Menus.menuDeposito();
            }
            if (nts < 0) {
                JOptionPane.showMessageDialog(null,
                        "Poxa EdneyA :("
                        + "\n Não é possivel reabastecer"
                        + "\n com um valor negativo de notas", "Menu Deposito", JOptionPane.ERROR_MESSAGE);
                Menus.menuDeposito();
            }

            if (nota == 1) {
                nt100 = nt100 + nts;
            }
            if (nota == 2) {
                nt50 = nt50 + nts;
            }
            if (nota == 3) {
                nt20 = nt20 + nts;
            }
            if (nota == 4) {
                nt10 = nt10 + nts;
            }
            if (nota == 5) {
                nt5 = nt5 + nts;
            }
            if (nota == 6) {
                nt2 = nt2 + nts;
            }

            JOptionPane.showMessageDialog(null,
                    "Foram reabastecidas " + nts
                    + "\n notas de R$" + nt
                    + "\n Com Sucesso !", "Menu Deposito", JOptionPane.INFORMATION_MESSAGE);

            Menus.menuDeposito();

        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null,
                    "Poxa Edneya :( \n"
                    + "Valor Invalido! \n"
                    + "Favor Parar de Avacalhar! :)", "Menu Deposito", JOptionPane.ERROR_MESSAGE);
            reab(nota);
        }

    }

}
