const Sequelize = require("sequelize");
const sequelize = require("../database/connection");

const Order = sequelize.define('Order', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    MenuID: {
        type: Sequelize.INTEGER,
        references: {
            model: 'menus',
            key: 'id'
        }
    },
    nameMenu: {
        type: Sequelize.STRING(100),
        allowNull: false,
        validate: {
            notEmpty: { msg: "Menu must have a value" }
        }
    },
    priceMenu: {
        type: Sequelize.DECIMAL(20),
        allowNull: false,
        defaultValue: 0,
        validate: {
            notEmpty: { msg: "Price must have a value" }
        }
    },
    // Tipe menu: makanan dan minuman
    qty: {
        type: Sequelize.INTEGER,
        allowNull: false,
        defaultValue: 1,
        validate: {
            notEmpty: { msg: "Menu must have value" },
            notNull: { msg: "Menu must not null" }
        }
    }
});

// Define association
Order.associate = function (models) {
    Order.hasMany(models.Transaction, { foreignKey: 'OrderID' });
    Order.belongsTo(models.Menu, { foreignKey: 'MenuID' })
}

module.exports = Order;