INSERT INTO Usuario(nome, email, senha, total_compras) VALUES ('nome', 'example@email.com', 'a', 0);
INSERT INTO Tamanho(descricao) VALUES ('37'), ('38'), ('39'), ('40'), ('41');
INSERT INTO Chinelo(nome, descricao, url_imagem, valor, media_nota, total_vendas, total_estrelas, data_criacao) VALUES ('chinelo', 'cu', 'cu', 200, 0, 0, 0, '2020-05-05'), ('chinelo2', 'cuu', 'cuu', 300, 0, 0, 0, '2020-05-05');
INSERT INTO Chinelo_has_tamanho(chinelo_id, tamanho_id) VALUES ('1', '1'), ('2', '1'), ('2', '2'), ('2', '3'), ('2', '4'); 
INSERT INTO Carrinho(qtd_itens_carrinho, total_carrinho, usuario_id) VALUES (0, 0, 1);
UPDATE Usuario SET carrinho_id = 1;
