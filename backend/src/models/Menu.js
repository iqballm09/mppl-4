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
        validate: {
            notEmpty: { msg: "Menu name must have a value" }
        }
    },
    price: {
        type: Sequelize.DECIMAL(20),
        defaultValue: 0,
        validate: {
            notEmpty: { msg: "Menu price must have a value" }
        }
    },
    photo: {
        type: Sequelize.STRING(1024),
        defaultValue: "default-photo.png"
    },
    // Tipe menu: makanan dan minuman
    type: {
        type: Sequelize.STRING(50),
        validate: {
            notEmpty: { msg: "Menu type must have value" }
        }
    }
});

// Define association
Menu.associate = function (models) {
    Menu.hasMany(models.Order, { foreignKey: 'MenuID' });
    Menu.belongsTo(models.Merchant, { foreignKey: 'MerchantID' })
}

module.exports = Menu;