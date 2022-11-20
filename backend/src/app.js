const sequelize = require("./database/connection");
const bodyParser = require("body-parser");
const routes = require("./routes");
const express = require("express");

const app = express();
app.use(bodyParser.json());

app.use("/api", routes);
app.use(function (req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
    res.setHeader('Access-Control-Allow-Credentials', true);
    next();
});

// Testing connection
app.listen(process.env.PORT || 5000, async () => {
    try {
        await sequelize.authenticate();
        console.log("Database has been connected!");
    } catch (error) {
        console.error("Unable to connect to the database:", error);
    }
});

