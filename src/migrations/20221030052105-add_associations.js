'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  up(queryInterface, Sequelize) {
    return queryInterface.addColumn(
      'cards', // name of Source model
      'UserID', // name of the key we're adding
      {
        type: Sequelize.INTEGER,
        references: {
          model: 'users', // name of Target model
          key: 'id', // key in Target model that we're referencing
        },
        onUpdate: 'CASCADE',
        onDelete: 'SET NULL',
      }
    )
      .then(() => {
        // Foodcourt hasMany Merchants
        return queryInterface.addColumn(
          'merchants', // name of Target model
          'FoodCourtID', // name of the key we're adding
          {
            type: Sequelize.INTEGER,
            references: {
              model: 'foodcourts', // name of Source model
              key: 'id',
            },
            onUpdate: 'CASCADE',
            onDelete: 'SET NULL',
          }
        );
      })
      .then(() => {
        // Card hasMany Top Up
        return queryInterface.addColumn(
          'topups', // name of Target model
          'CardID', // name of the key we're adding
          {
            type: Sequelize.INTEGER,
            references: {
              model: 'cards', // name of Source model
              key: 'id',
            },
            onUpdate: 'CASCADE',
            onDelete: 'SET NULL',
          }
        );
      })
      .then(() => {
        // Card hasMany Payment
        return queryInterface.addColumn(
          'payments', // name of Target model
          'CardID', // name of the key we're adding
          {
            type: Sequelize.INTEGER,
            references: {
              model: 'cards', // name of Source model
              key: 'id',
            },
            onUpdate: 'CASCADE',
            onDelete: 'SET NULL',
          }
        );
      })
      .then(() => {
        // Card hasMany Payment
        return queryInterface.addColumn(
          'menus', // name of Target model
          'MerchantID', // name of the key we're adding
          {
            type: Sequelize.INTEGER,
            references: {
              model: 'merchants', // name of Source model
              key: 'id',
            },
            onUpdate: 'CASCADE',
            onDelete: 'SET NULL',
          }
        );
      })
      .then(() => {
        // Card hasMany Payment
        return queryInterface.addColumn(
          'transactions', // name of Source model
          'UserID', // name of the key we're adding
          {
            type: Sequelize.INTEGER,
            references: {
              model: 'users', // name of Target model
              key: 'id', // key in Target model that we're referencing
            },
            onUpdate: 'CASCADE',
            onDelete: 'SET NULL',
          }
        )
      })
      .then(() => {
        // Card hasMany Payment
        return queryInterface.addColumn(
          'transactions', // name of Target model
          'MerchantID', // name of the key we're adding
          {
            type: Sequelize.INTEGER,
            references: {
              model: 'merchants', // name of Source model
              key: 'id',
            },
            onUpdate: 'CASCADE',
            onDelete: 'SET NULL',
          }
        );
      })
      .then(() => {
        // Card hasMany Payment
        return queryInterface.addColumn(
          'transactions', // name of Target model
          'MenuID', // name of the key we're adding
          {
            type: Sequelize.INTEGER,
            references: {
              model: 'menus', // name of Source model
              key: 'id',
            },
            onUpdate: 'CASCADE',
            onDelete: 'SET NULL',
          }
        );
      })
      .then(() => {
        // Payment hasOne Order
        return queryInterface.addColumn(
          'transactions', // name of Target model
          'PaymentID', // name of the key we're adding
          {
            type: Sequelize.INTEGER,
            references: {
              model: 'payments', // name of Source model
              key: 'id',
            },
            onUpdate: 'CASCADE',
            onDelete: 'SET NULL',
          }
        );
      })
  },

  down: (queryInterface, Sequelize) => {
    return queryInterface.removeColumn(
      'cards', // name of Source model
      'UserID' // key we want to remove
    )
      .then(() => {
        return queryInterface.removeColumn(
          'merchants', // name of the Target model
          'FoodCourtID' // key we want to remove
        );
      })
      .then(() => {
        return queryInterface.removeColumn(
          'topups', // name of the Target model
          'CardID' // key we want to remove
        );
      })
      .then(() => {
        return queryInterface.removeColumn(
          'payments', // name of the Target model
          'CardID' // key we want to remove
        );
      })
      .then(() => {
        return queryInterface.removeColumn(
          'menus', // name of the Target model
          'MerchantID' // key we want to remove
        );
      })
      .then(() => {
        return queryInterface.removeColumn(
          'transactions', // name of Source model
          'UserID' // key we want to remove
        )
      })
      .then(() => {
        return queryInterface.removeColumn(
          'transactions', // name of Source model
          'MerchantID' // key we want to remove
        )
      })
      .then(() => {
        return queryInterface.removeColumn(
          'transactions', // name of Source model
          'MenuID' // key we want to remove
        )
      })
      .then(() => {
        // remove Payment hasOne Order
        return queryInterface.removeColumn(
          'transactions', // name of the Target model
          'PaymentID' // key we want to remove
        );
      })
  }
};