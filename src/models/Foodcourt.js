const Sequelize = require("Sequelize");
const sequelize = require("../database/connection");

const FoodCourt = sequelize.define("FoodCourt", {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: Sequelize.STRING(100),
        allowNull: false,
        validate: {
            notNull: { msg: 'Foodcourt must have a name' },
            notEmpty: { msg: 'name must not be empty' }
        }
    },
    location: {
        type: Sequelize.STRING(100),
        alloNull: false,
        validate: {
            notNull: { msg: 'Foodcourt must have a location' },
            notEmpty: { msg: 'location must not be empty' }
        }
    },
    income: {
        type: Sequelize.DECIMAL(20),
        allowNull: false,
        defaultValue: 0,
        validate: {
            notNull: { msg: 'Foodcourt must have an income' },
            notEmpty: { msg: 'income must not be empty' }
        }
    },
    numberMerchants: {
        type: Sequelize.INTEGER,
        defaultValue: 1
    }
});

// Define association
FoodCourt.associate = function () {
    FoodCourt.hasMany(models.Merchant, { foreignKey: 'FoodCourtID' });
}

module.exports = FoodCourt;