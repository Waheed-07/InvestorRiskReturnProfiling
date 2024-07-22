import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fim-sett-acct-wq.reducer';

export const FimSettAcctWqDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fimSettAcctWqEntity = useAppSelector(state => state.fim.fimSettAcctWq.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fimSettAcctWqDetailsHeading">
          <Translate contentKey="fimApp.fimFimSettAcctWq.detail.title">FimSettAcctWq</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.id}</dd>
          <dt>
            <span id="settaccId">
              <Translate contentKey="fimApp.fimFimSettAcctWq.settaccId">Settacc Id</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.settaccId}</dd>
          <dt>
            <span id="accountId">
              <Translate contentKey="fimApp.fimFimSettAcctWq.accountId">Account Id</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.accountId}</dd>
          <dt>
            <span id="settAcctNbr">
              <Translate contentKey="fimApp.fimFimSettAcctWq.settAcctNbr">Sett Acct Nbr</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.settAcctNbr}</dd>
          <dt>
            <span id="settCcy">
              <Translate contentKey="fimApp.fimFimSettAcctWq.settCcy">Sett Ccy</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.settCcy}</dd>
          <dt>
            <span id="settAcctStatus">
              <Translate contentKey="fimApp.fimFimSettAcctWq.settAcctStatus">Sett Acct Status</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.settAcctStatus}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="fimApp.fimFimSettAcctWq.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.createdBy}</dd>
          <dt>
            <span id="createdTs">
              <Translate contentKey="fimApp.fimFimSettAcctWq.createdTs">Created Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimSettAcctWqEntity.createdTs ? (
              <TextFormat value={fimSettAcctWqEntity.createdTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="fimApp.fimFimSettAcctWq.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.updatedBy}</dd>
          <dt>
            <span id="updatedTs">
              <Translate contentKey="fimApp.fimFimSettAcctWq.updatedTs">Updated Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimSettAcctWqEntity.updatedTs ? (
              <TextFormat value={fimSettAcctWqEntity.updatedTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="fimApp.fimFimSettAcctWq.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.recordStatus}</dd>
          <dt>
            <span id="uploadRemark">
              <Translate contentKey="fimApp.fimFimSettAcctWq.uploadRemark">Upload Remark</Translate>
            </span>
          </dt>
          <dd>{fimSettAcctWqEntity.uploadRemark}</dd>
        </dl>
        <Button tag={Link} to="/fim/fim-sett-acct-wq" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fim/fim-sett-acct-wq/${fimSettAcctWqEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FimSettAcctWqDetail;
