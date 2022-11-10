'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    return queryInterface.createTable("orders", {
      id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
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
      },
      createdAt: Sequelize.DATE,
      updatedAt: Sequelize.DATE
    });
  },

  async down(queryInterface, Sequelize) {
    return queryInterface.dropTable("orders");
  }
};
