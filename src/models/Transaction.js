const Sequelize = require("Sequelize");
const sequelize = require("../database/connection");

const Transaction = sequelize.define("Transaction", {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    UserID: {
        type: Sequelize.INTEGER,
        references: {
            model: 'users',
            key: 'id'
        }
    },
    MerchantID: {
        type: Sequelize.INTEGER,
        references: {
            model: 'merchants',
            key: 'id'
        }
    },
    PaymentID: {
        type: Sequelize.INTEGER,
        references: {
            model: 'payments',
            key: 'id'
        }
    },
    MenuID: {
        type: Sequelize.INTEGER,
        references: {
            model: 'menus',
            key: 'id'
        }
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
Transaction.associate = function (models) {
    Transaction.belongsTo(models.User, { foreignKey: 'UserID' });
    Transaction.belongsTo(models.Menu, { foreignKey: 'MenuID' });
    Transaction.belongsTo(models.Merchant, { foreignKey: 'MerchantID' });
    Transaction.belongsTo(models.Payment, { foreignKey: 'PaymentID' });
}

module.exports = Transaction;