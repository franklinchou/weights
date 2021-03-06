import { module, test } from 'qunit';
import { visit, click, currentURL } from '@ember/test-helpers';
import { setupApplicationTest } from 'ember-qunit';

module('Acceptance | list sessions', function(hooks) {
  setupApplicationTest(hooks);

  test('should show my sessions as home', async function(assert) {
    await visit('/');
    assert.equal(currentURL(), '/sessions', 'should redirect to /sessions');
  });

  test('test about link', async function(assert) {
    await visit('/');
    await click(".menu-about");
    assert.equal(currentURL(), '/about', 'should navigate to /about')
  });

});
