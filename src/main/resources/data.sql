INSERT INTO Usuario(nome, email, senha, total_compras) VALUES ('ADMIN', 'administrador@email.com', '$2a$10$Et5x4t2Ga79x6TncidG6ZehjfDt8gjcE0jhiRkM/CFlQI2lrLFuDK', 0);
INSERT INTO Usuario(nome, email, senha, total_compras) VALUES ('Cliente', 'cliente@email.com', '$2a$10$RZs3flTMp1vS4T6FgVWemuHPQXOq2IRQ64Ux93jLoFGkXNDNZfJDG', 0);
INSERT INTO Perfil(descricao) VALUES ('ADM'), ('Cliente');
INSERT INTO Tamanho(descricao) VALUES ('37'), ('38'), ('39'), ('40'), ('41');
INSERT INTO Chinelo(nome, descricao, url_imagem, valor, media_nota, total_vendas, total_estrelas, data_criacao) VALUES ('chinelo', 'cu', 'cu', 200, 0, 0, 0, '2020-05-05'), ('chinelo2', 'cuu', 'cuu', 300, 0, 0, 0, '2020-05-05');
INSERT INTO Chinelo_has_tamanho(chinelo_id, tamanho_id) VALUES ('1', '1'), ('2', '1'), ('2', '2'), ('2', '3'), ('2', '4'); 
INSERT INTO Carrinho(qtd_itens_carrinho, total_carrinho, usuario_id) VALUES (0, 0, 1);
INSERT INTO Carrinho(qtd_itens_carrinho, total_carrinho, usuario_id) VALUES (0, 0, 2);
INSERT INTO Cartao(titular, numero_cartao, codigo_seguranca, vencimento) VALUES ('nome', '123.456.789', '123', '2020-05-05');
INSERT INTO Endereco(bairro, cidade, complemento, logradouro, numero, uf) VALUES('bairro', 'cidade', 'complemento', 'logradouro', 'num', 'uf');
UPDATE Usuario SET carrinho_id = 1 WHERE id = 1;
UPDATE Usuario SET carrinho_id = 2 WHERE id = 2;
INSERT INTO Chinelo(nome, descricao, url_imagem, valor, media_nota, total_vendas, total_estrelas, data_criacao)
VALUES('Chinelo arco-iris', 'Chinelo havainas com estampa de arco-iris, para colorir seu dia', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQea9ZSxG5tqq5FHSi2Hp6oF4SzmBD6CchjCw&usqp=CAU', 20.00, 0, 0, 0, '2020-05-05');
VALUES('chinelo do Mickey', 'Chinelo do Mickey para tornar seu dia uma animação', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRaevSP63_KhXAZ25sM7Ys7ie9VxAB5JscFjQ&usqp=CAU',40.00, 0, 0, 0, '2020-07-05');
VALUES('Chinelo Simpsons', 'Chinelos dos simpsons, a familia amarela vai te acompanhar no seu dia a dia', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqf5u61UKwiC1m2f-AfeKjZPp4MKfQGz6bOA&usqp=CAU', 25.00, 0, 0, 0, '2020-05-03');