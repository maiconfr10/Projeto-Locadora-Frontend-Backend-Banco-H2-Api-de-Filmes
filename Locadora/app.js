const baseUrl = "http://localhost:8080";

// ---------------- SAFE FETCH ----------------
async function safeFetch(url, options = {}) {
    const response = await fetch(baseUrl + url, options);

    if (!response.ok) {
        const error = await response.text();
        throw new Error(error);
    }

    return response.json();
}

// ---------------- CLIENTE ----------------
document.getElementById("form-cliente").addEventListener("submit", async (e) => {
    e.preventDefault();

    const payload = {
        nome: document.getElementById("cliente-nome").value,
        cpf: document.getElementById("cliente-cpf").value,
        telefone: document.getElementById("cliente-telefone").value
    };

    try {
        await safeFetch("/cliente", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });
        alert("Cliente cadastrado!");
        listarClientes();
    } catch (err) {
        alert("Erro ao cadastrar cliente: " + err.message);
    }
});

async function listarClientes() {
    const lista = document.getElementById("lista-clientes");
    lista.innerHTML = "";

    try {
        const dados = await safeFetch("/cliente");
        dados.forEach(c => {
            lista.innerHTML += `<li>ID: ${c.idCliente} — ${c.nome} — CPF: ${c.cpf} — Tel: ${c.telefone}</li>`;
        });
    } catch (err) {
        alert("Erro ao listar clientes");
    }
}

// ---------------- FILME ----------------
document.getElementById("form-filme").addEventListener("submit", async (e) => {
    e.preventDefault();

    const payload = {
        titulo: document.getElementById("filme-titulo").value,
        genero: document.getElementById("filme-genero").value,
        anoLancamento: Number(document.getElementById("filme-ano").value)
    };

    try {
        await safeFetch("/filme", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });
        alert("Filme cadastrado!");
        listarFilmes();
    } catch (err) {
        alert("Erro ao cadastrar filme: " + err.message);
    }
});

async function listarFilmes() {
    const lista = document.getElementById("lista-filmes");
    lista.innerHTML = "";

    try {
        const dados = await safeFetch("/filme");
        dados.forEach(f => {
            lista.innerHTML += `<li>ID: ${f.idFilme} — ${f.titulo} — Gênero: ${f.genero} — Ano: ${f.anoLancamento}</li>`;
        });
    } catch (err) {
        alert("Erro ao listar filmes");
    }
}

// -------------------------------------------------
// 🔥 CONSULTA FILME EM API EXTERNA + IMAGEM
// -------------------------------------------------
document.getElementById("btn-buscar-externo").addEventListener("click", async () => {
    const titulo = document.getElementById("titulo-externo").value;

    if (!titulo.trim()) {
        alert("Digite um título!");
        return;
    }

    const divResultado = document.getElementById("resultado-externo");
    divResultado.innerHTML = "Buscando...";

    try {
        const filme = await safeFetch("/filme/externo?titulo=" + encodeURIComponent(titulo));

        // 🔥 Correção da imagem: evita "N/A", null, vazio
        const imagemValida =
            filme.imagem &&
            filme.imagem !== "N/A" &&
            filme.imagem.trim() !== "";

        divResultado.innerHTML = `
            <h3>Resultado:</h3>
            <p><strong>Título:</strong> ${filme.titulo}</p>
            <p><strong>Gênero:</strong> ${filme.genero}</p>
            <p><strong>Ano:</strong> ${filme.anoLancamento}</p>

            ${imagemValida ? `<img src="${filme.imagem}" width="200">` : "<p>Sem imagem disponível.</p>"}
        `;

        window.filmeExterno = filme;
        document.getElementById("btn-cadastrar-externo").style.display = "block";

    } catch (err) {
        divResultado.innerHTML = "<p>Filme não encontrado.</p>";
    }
});

// -------------------------------------------------
// 🔥 CADASTRAR FILME IMPORTADO
// -------------------------------------------------
document.getElementById("btn-cadastrar-externo").addEventListener("click", async () => {
    if (!window.filmeExterno) {
        alert("Nenhum filme carregado!");
        return;
    }

    try {
        await safeFetch("/filme", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(window.filmeExterno)
        });

        alert("Filme externo cadastrado!");
        listarFilmes();

    } catch (err) {
        alert("Erro ao cadastrar filme importado: " + err.message);
    }
});

// ---------------- EMPRÉSTIMO ----------------
document.getElementById("form-emprestimo").addEventListener("submit", async (e) => {
    e.preventDefault();

    const payload = {
        dataEmprestimo: document.getElementById("emprestimo-data").value,
        dataDevolucao: document.getElementById("emprestimo-devolucao").value,
        status: document.getElementById("emprestimo-status").value,
        valor: Number(document.getElementById("emprestimo-valor").value),

        filme: {
            idFilme: Number(document.getElementById("emprestimo-filme").value)
        },
        cliente: {
            idCliente: Number(document.getElementById("emprestimo-cliente").value)
        }
    };

    try {
        await safeFetch("/emprestimo", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        alert("Empréstimo registrado!");
        listarEmprestimos();
    } catch (err) {
        alert("Erro ao registrar empréstimo: " + err.message);
    }
});

// LISTAR EMPRÉSTIMOS
async function listarEmprestimos() {
    const lista = document.getElementById("lista-emprestimos");
    lista.innerHTML = "";

    try {
        const dados = await safeFetch("/emprestimo");
        dados.forEach(e => {
            lista.innerHTML += `
                <li>
                    ID: ${e.idEmprestimo} —
                    Cliente: ${e.cliente?.idCliente} —
                    Filme: ${e.filme?.idFilme} —
                    Status: ${e.status} —
                    Valor: R$ ${e.valor} —
                    Devolução: ${e.dataDevolucao}
                </li>`;
        });
    } catch (err) {
        alert("Erro ao listar empréstimos");
    }
}
