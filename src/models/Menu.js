const Sequelize = require("Sequelize");
const sequelize = require("../database/connection");

const Menu = sequelize.define('Menu', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    MerchantID: {
        type: Sequelize.INTEGER
    },
    name: {
        type: Sequelize.STRING(100),
        allowNull: false,
        validate: {
            notEmpty: { msg: "Menu must have a value" }
        }
    },
    price: {
        type: Sequelize.DECIMAL(20),
        allowNull: false,
        validate: {
            notEmpty: { msg: "Price must have a value" }
        }
    },
    // Tipe menu: makanan dan minuman
    type: {
        type: Sequelize.STRING(50),
        allowNull: false,
        validate: {
            notEmpty: { msg: "Menu must have value" },
            notNull: { msg: "Menu must not null" }
        }
    }
});

// Define association
Menu.associate = function () {
    Menu.hasMany(models.Transaction, { foreignKey: 'MenuID' });
}

module.exports = Menu;