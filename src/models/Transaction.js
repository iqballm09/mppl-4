const Sequelize = require("Sequelize");
const sequelize = require("../database/connection");

const Transaction = sequelize.define("Transaction", {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    UserID: {
        type: Sequelize.INTEGER
    },
    MerchantID: {
        type: Sequelize.INTEGER
    },
    PaymentID: {
        type: Sequelize.INTEGER
    },
    MenuID: {
        type: Sequelize.INTEGER
    },
    menuName: {
        type: Sequelize.STRING(100)
    },
    price: {
        type: Sequelize.DECIMAL(20),
        defaultValue: 0,
        validate: {
            notEmpty: { msg: "Price must not empty" }
        }
    },
    qty: {
        type: Sequelize.INTEGER,
        defaultValue: 0,
        validate: {
            notEmpty: { msg: "Qty must not empty" }
        }
    },
    detail: {
        type: Sequelize.STRING(500)
    },
    date: {
        type: Sequelize.DATEONLY
    }
});

// Define associations
Transaction.associate = function() {
    Transaction.belongsTo(models.User, { foreignKey: 'UserID' });
}

module.exports = Transaction;