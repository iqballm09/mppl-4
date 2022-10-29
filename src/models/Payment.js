const Sequelize = require("Sequelize");
const sequelize = require("../database/connection");

const Payment = sequelize.define("Payment", {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    amount: {
        type: Sequelize.DECIMAL(20),
        defaultValue: 0
    },
    foodcourtName: {
        type: Sequelize.STRING(100)
    },
    merchantName: {
        type: Sequelize.STRING(100)
    },
    date: {
        type: Sequelize.DATEONLY
    }
});

module.exports = Payment;