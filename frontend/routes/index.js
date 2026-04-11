const express = require('express');
const router = express.Router();
router.get('/', async (req, res) => {
    try {
        const fetchResponse = await fetch('http://localhost:8080/api/articles');
        const result = await fetchResponse.json();
        const articlesList = result.response || [];

        res.render('index', {
            title: 'Personal Blog - Live',
            articles: articlesList
        });

    } catch (error) {
        console.error("Fetch Error:", error.message);
        res.render('index', { title: 'Service Unavailable', articles: [] });
    }
});

// CREATE
router.post('/api/articles', async (req, res) => {
    const response = await fetch('http://localhost:8080/api/articles', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(req.body)
    });
    const data = await response.json();
    res.json(data);
});

// DELETE
router.delete('/api/articles/:id', async (req, res) => {
    await fetch(`http://localhost:8080/api/articles/${req.params.id}`, { method: 'DELETE' });
    res.sendStatus(200);
});

// UPDATE
router.put('/api/articles/:id', async (req, res) => {
    const response = await fetch(`http://localhost:8080/api/articles/${req.params.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(req.body)
    });
    const data = await response.json();
    res.json(data);
});

// GET SINGLE
router.get('/article/:id', async (req, res) => {
    try {
        const fetchResponse = await fetch(`http://localhost:8080/api/articles/${req.params.id}`);
        const result = await fetchResponse.json();
        const article = result.response;

        if (!article) {
            return res.status(404).send("Article not found in the database.");
        }

        res.render('article', {
            article: article
        });
    } catch (error) {
        console.error("Fetch Error:", error.message);
        res.status(500).send("Internal Server Error: Connection to Java failed.");
    }
});
module.exports = router;