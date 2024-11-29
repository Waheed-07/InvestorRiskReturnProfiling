import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fim-sett-acct-history.reducer';

export const FimSettAcctHistoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fimSettAcctHistoryEntity = useAppSelector(state => state.fim.fimSettAcctHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fimSettAcctHistoryDetailsHeading">
          <Translate contentKey="fimApp.fimFimSettAcctHistory.detail.title">FimSettAcctHistory</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctHistoryEntity.id}</dd>
          <dt>
            <span id="settaccId">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.settaccId">Settacc Id</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctHistoryEntity.settaccId}</dd>
          <dt>
            <span id="historyTs">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.historyTs">History Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimSettAcctHistoryEntity.historyTs ? (
              <TextFormat value={fimSettAcctHistoryEntity.historyTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="accountId">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.accountId">Account Id</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctHistoryEntity.accountId}</dd>
          <dt>
            <span id="settAcctNbr">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.settAcctNbr">Sett Acct Nbr</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctHistoryEntity.settAcctNbr}</dd>
          <dt>
            <span id="settCcy">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.settCcy">Sett Ccy</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctHistoryEntity.settCcy}</dd>
          <dt>
            <span id="settAcctStatus">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.settAcctStatus">Sett Acct Status</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctHistoryEntity.settAcctStatus}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctHistoryEntity.createdBy}</dd>
          <dt>
            <span id="createdTs">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.createdTs">Created Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimSettAcctHistoryEntity.createdTs ? (
              <TextFormat value={fimSettAcctHistoryEntity.createdTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctHistoryEntity.updatedBy}</dd>
          <dt>
            <span id="updatedTs">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.updatedTs">Updated Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimSettAcctHistoryEntity.updatedTs ? (
              <TextFormat value={fimSettAcctHistoryEntity.updatedTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="fimApp.fimFimSettAcctHistory.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctHistoryEntity.recordStatus}</dd>
        </dl>
        <Button tag={Link} to="/fim/fim-sett-acct-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fim/fim-sett-acct-history/${fimSettAcctHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FimSettAcctHistoryDetail;
