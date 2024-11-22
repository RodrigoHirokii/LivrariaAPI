document.addEventListener('DOMContentLoaded', function () {
    const adicionarButton = document.getElementById('adicionar');
    const pesquisarButton = document.getElementById('pesquisar');
    const nomeInput = document.getElementById('nome');
    const pesquisarNomeInput = document.getElementById('pesquisarNome');
    const bibliotecasUl = document.getElementById('bibliotecasUl');
    
    const baseUrl = 'http://localhost:8080/biblioteca'; // Substitua pelo seu URL do backend

    // Função para adicionar uma biblioteca
    adicionarButton.addEventListener('click', () => {
        const nome = nomeInput.value.trim();
        if (nome === "") {
            alert("Digite o nome do jogo!");
            return;
        }

        const biblioteca = { nome: nome };
        
        fetch(baseUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(biblioteca)
        })
        .then(response => {
            if (response.ok) {
                nomeInput.value = ''; // Limpar o campo de entrada
                alert("Jogo adicionado com sucesso!");
                carregarBibliotecas(); // Recarrega as bibliotecas após a adição
            } else {
                alert("Erro ao adicionar jogo.");
            }
        });
    });

    // Função para pesquisar bibliotecas
    pesquisarButton.addEventListener('click', () => {
        const nomePesquisado = pesquisarNomeInput.value.trim();
        if (nomePesquisado === "") {
            carregarBibliotecas(); // Se não há pesquisa, carrega todas as bibliotecas
        } else {
            pesquisarBibliotecas(nomePesquisado);
        }
    });

    // Função para carregar as bibliotecas
    function carregarBibliotecas() {
        fetch(baseUrl)
            .then(response => response.json())
            .then(data => {
                bibliotecasUl.innerHTML = ''; // Limpa a lista antes de preencher
                data.forEach(biblioteca => {
                    const li = document.createElement('li');
                    li.textContent = biblioteca.nome;

                    // Botão de excluir
                    const excluirButton = document.createElement('button');
                    excluirButton.textContent = 'Excluir';
                    excluirButton.classList.add('excluir-button');
                    excluirButton.addEventListener('click', () => excluirBiblioteca(biblioteca.id));

                    li.appendChild(excluirButton);
                    bibliotecasUl.appendChild(li);
                });
            });
    }

    // Função para excluir uma biblioteca
    function excluirBiblioteca(id) {
        fetch(`${baseUrl}/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                alert("Jogo excluído com sucesso!");
                carregarBibliotecas(); // Recarrega a lista após a exclusão
            } else {
                alert("Erro ao excluir jogo.");
            }
        });
    }

    // Função para pesquisar bibliotecas com nome
    function pesquisarBibliotecas(nome) {
        fetch(`${baseUrl}?nome=${nome}`)
            .then(response => response.json())
            .then(data => {
                bibliotecasUl.innerHTML = ''; // Limpa a lista antes de preencher
                data.forEach(biblioteca => {
                    const li = document.createElement('li');
                    li.textContent = biblioteca.nome;

                    // Botão de excluir
                    const excluirButton = document.createElement('button');
                    excluirButton.textContent = 'Excluir';
                    excluirButton.classList.add('excluir-button');
                    excluirButton.addEventListener('click', () => excluirBiblioteca(biblioteca.id));

                    li.appendChild(excluirButton);
                    bibliotecasUl.appendChild(li);
                });
            });
    }

    // Carregar bibliotecas ao iniciar a página
    carregarBibliotecas();
});
