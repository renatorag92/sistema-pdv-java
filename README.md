# 🛒 Sistema PDV em Java (Swing)

Sistema de ponto de venda (PDV) desenvolvido em Java utilizando Swing, aplicando conceitos de Programação Orientada a Objetos e organização em camadas.

---

## 🏗 Arquitetura

O projeto está organizado em camadas:

- **model** → Entidades do sistema
- **dao** → Camada de acesso e persistência de dados
- **view** → Interface gráfica (Swing)
- **pagamento** → Lógica relacionada às formas de pagamento
- **theme** → Personalização visual da interface

---

## 💾 Persistência de Dados

Atualmente o sistema mantém os dados em memória utilizando estruturas como `HashMap`, 
com serialização em arquivos `.ser` para armazenamento simplificado.

Essa abordagem foi adotada para priorizar a aplicação de conceitos de POO e organização arquitetural.

🔜 Como evolução futura, o projeto poderá ser adaptado para utilizar um banco de dados relacional (ex: MySQL ou PostgreSQL), implementando uma camada de persistência mais robusta e escalável.

---

## ▶ Como executar

1. Abrir o projeto no NetBeans
2. Executar a aplicação

---

## 🚀 Melhorias Futuras

- Implementação de banco de dados relacional
- Aplicação completa do padrão MVC
- Implementação de testes automatizados
- Refatoração para versão web ou JavaFX
