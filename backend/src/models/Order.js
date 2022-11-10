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
    nameMenu: {
        type: Sequelize.STRING(100),
        validate: {
            notEmpty: { msg: "Menu name must have a value" }
        }
    },
    priceMenu: {
        type: Sequelize.DECIMAL(20),
        defaultValue: 0,
        validate: {
            notEmpty: { msg: "Menu price must have a value" }
        }
    },
    // Tipe menu: makanan dan minuman
    qty: {
        type: Sequelize.INTEGER,
        defaultValue: 1,
        validate: {
            notEmpty: { msg: "Qty must have value" },
            notNull: { msg: "Qty must not null" }
        }
    },
    date: {
        type: Sequelize.DATEONLY
    }
});

// Define association
Order.associate = function (models) {
    Order.belongsTo(models.User, { foreignKey: 'UserID' });
    Order.belongsTo(models.Merchant, { foreignKey: 'MerchantID' });
    Order.belongsTo(models.Menu, { foreignKey: 'MenuID' });
}

module.exports = Order;