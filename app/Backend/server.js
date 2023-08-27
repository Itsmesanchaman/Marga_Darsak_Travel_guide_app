const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const port = 3000;

// Middleware to parse JSON data
app.use(bodyParser.json());

// Handle POST request to '/api/messages'
app.post('/api/messages', (req, res) => {
  const message = req.body.message;
  console.log('Received message:', message);
  // Perform any necessary processing or save the message to a database

  // Send a response back to the client
  res.json({ success: true, message: 'Message received' });
});

// Start the server
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
