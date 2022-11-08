const Sequelize = require("sequelize");
const sequelize = require("../database/connection");

const Menu = sequelize.define('Menu', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    MerchantID: {
        type: Sequelize.INTEGER,
        references: {
            model: 'merchants',
            key: 'id'
        }
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
        defaultValue: 0,
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
Menu.associate = function (models) {
    Menu.hasMany(models.Order, { foreignKey: 'MenuID' });
    Menu.belongsTo(models.Merchant, { foreignKey: 'MerchantID' })
}

module.exports = Menu;