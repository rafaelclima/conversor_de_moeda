# Conversor de Moedas 💰

![Java](https://img.shields.io/badge/Java-14%2B-blue?style=for-the-badge&logo=openjdk)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-green?style=for-the-badge)

Um aplicativo desktop simples e intuitivo para conversão de moedas em tempo real, construído com Java Swing. 💹

## 📖 Sobre o Projeto

Este projeto é um conversor de moedas que permite ao usuário inserir um valor em uma determinada moeda e visualizar instantaneamente a conversão correspondente em diversas outras moedas. Ele utiliza a API [ExchangeRate-API](https://www.exchangerate-api.com/) para buscar as taxas de câmbio mais recentes, garantindo dados atualizados.

O objetivo principal é fornecer uma ferramenta prática e fácil de usar para consultas rápidas de câmbio.

## ✨ Funcionalidades

*   **Conversão Múltipla:** Converta um valor de uma moeda para várias outras simultaneamente.
*   **Moedas Suportadas:** Inclui Real (BRL), Dólar Americano (USD), Euro (EUR), Libra Esterlina (GBP), Peso Argentino (ARS), Dólar Canadense (CAD) e Franco Suíço (CHF).
*   **Interface Gráfica Amigável:** Construída com Java Swing para uma experiência de usuário clara e direta.
*   **Atualização Dinâmica:** Ao inserir um valor e clicar no botão da moeda correspondente, todos os campos são atualizados com as taxas convertidas.
*   **Validação de Entrada:** Verifica se o valor inserido é um número válido antes de processar a conversão.
*   **Feedback ao Usuário:** Exibe mensagens de erro ou informação através de `JOptionPane`.

## 🛠️ Tecnologias Utilizadas

*   **Java:** Linguagem de programação principal.
    *   Uso de Records (Java 14+) para DTOs (e.g., `ApiRecord`).
    *   Uso de `var` para inferência de tipo (Java 10+).
*   **Java Swing:** Para a construção da interface gráfica do usuário (GUI).
*   **Gson:** Biblioteca do Google para serialização e desserialização de objetos Java para JSON (e vice-versa), utilizada para processar a resposta da API.
*   **ExchangeRate-API:** API externa para obter dados de taxas de câmbio em tempo real.

## 🚀 Como Executar

1.  **Pré-requisitos:**
    *   Java Development Kit (JDK) 14 ou superior instalado.
    *   A biblioteca Gson. Se você não estiver usando um gerenciador de dependências como Maven ou Gradle, precisará adicionar o JAR do Gson ao classpath do seu projeto.

2.  **Clone o repositório (opcional, se você já tem os arquivos):**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd conversor_de_moeda
    ```

3.  **Compile e Execute:**
    *   **Usando uma IDE (Recomendado):**
        *   Importe o projeto na sua IDE Java preferida (IntelliJ IDEA, Eclipse, VS Code com o Java Extension Pack).
        *   Certifique-se de que a biblioteca Gson está configurada corretamente no build path do projeto.
        *   Localize e execute o método `main` na classe `App.java` (localizada em `src/App.java`).
    *   **Via Linha de Comando:**
        Navegue até o diretório `src` do projeto. Supondo que o JAR do Gson (ex: `gson-2.10.1.jar`) esteja em uma pasta `lib` na raiz do projeto:
        ```bash
        # Compilar (ajuste o caminho para o gson.jar se necessário)
        javac -cp ../lib/gson-2.10.1.jar:. App.java br/com/rafael/model/Moeda.java br/com/rafael/services/CurrencyApi.java br/com/rafael/services/ApiRecord.java br/com/rafael/view/Separador.java

        # Executar (ajuste o caminho para o gson.jar se necessário)
        java -cp ../lib/gson-2.10.1.jar:. App
        ```

## 🔑 Chave da API

Para uso pessoal ou em produção, é altamente recomendável:
1.  Registrar-se no ExchangeRate-API para obter sua própria chave gratuita.
2.  Não embutir a chave diretamente no código-fonte. Em vez disso, utilize variáveis de ambiente ou arquivos de configuração para maior segurança e flexibilidade.

## 🤝 Contribuições

Sinta-se à vontade para bifurcar o projeto, abrir *issues* e enviar *pull requests* com melhorias!

---

Desenvolvido por [Rafael Lima/@rafaelclima]