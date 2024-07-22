import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fim-sett-acct.reducer';

export const FimSettAcctDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fimSettAcctEntity = useAppSelector(state => state.fim.fimSettAcct.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fimSettAcctDetailsHeading">
          <Translate contentKey="fimApp.fimFimSettAcct.detail.title">FimSettAcct</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctEntity.id}</dd>
          <dt>
            <span id="settaccId">
              <Translate contentKey="fimApp.fimFimSettAcct.settaccId">Settacc Id</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctEntity.settaccId}</dd>
          <dt>
            <span id="accountId">
              <Translate contentKey="fimApp.fimFimSettAcct.accountId">Account Id</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctEntity.accountId}</dd>
          <dt>
            <span id="settAcctNbr">
              <Translate contentKey="fimApp.fimFimSettAcct.settAcctNbr">Sett Acct Nbr</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctEntity.settAcctNbr}</dd>
          <dt>
            <span id="settCcy">
              <Translate contentKey="fimApp.fimFimSettAcct.settCcy">Sett Ccy</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctEntity.settCcy}</dd>
          <dt>
            <span id="settAcctStatus">
              <Translate contentKey="fimApp.fimFimSettAcct.settAcctStatus">Sett Acct Status</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctEntity.settAcctStatus}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="fimApp.fimFimSettAcct.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctEntity.createdBy}</dd>
          <dt>
            <span id="createdTs">
              <Translate contentKey="fimApp.fimFimSettAcct.createdTs">Created Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimSettAcctEntity.createdTs ? <TextFormat value={fimSettAcctEntity.createdTs} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="fimApp.fimFimSettAcct.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctEntity.updatedBy}</dd>
          <dt>
            <span id="updatedTs">
              <Translate contentKey="fimApp.fimFimSettAcct.updatedTs">Updated Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimSettAcctEntity.updatedTs ? <TextFormat value={fimSettAcctEntity.updatedTs} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="fimApp.fimFimSettAcct.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctEntity.recordStatus}</dd>
        </dl>
        <Button tag={Link} to="/fim/fim-sett-acct" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fim/fim-sett-acct/${fimSettAcctEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FimSettAcctDetail;
