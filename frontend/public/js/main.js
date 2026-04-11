function openModal(id = '', title = '', content = '') {
    document.getElementById('article-id').value = id;
    document.getElementById('input-title').value = title;
    document.getElementById('input-content').value = content;
    document.getElementById('modal-title').innerText = id ? 'Edit Article' : 'New Article';
    document.getElementById('modal').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
}

function closeModal() {
    document.getElementById('modal').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
}

async function saveArticle() {
    const id = document.getElementById('article-id').value;
    const article = {
        title: document.getElementById('input-title').value,
        content: document.getElementById('input-content').value,
        date: new Date().toISOString().split('T')[0]
    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `/api/articles/${id}` : '/api/articles';

    const res = await fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(article)
    });

    if (res.ok) window.location.reload();
}

async function deleteArticle(id) {
    if (confirm('Are you sure? Distraction and deletion are irreversible.')) {
        const res = await fetch(`/api/articles/${id}`, { method: 'DELETE' });
        if (res.ok) document.getElementById(`article-${id}`).remove();
    }
}

function editArticle(id, title, content) {
    openModal(id, title, content);
}